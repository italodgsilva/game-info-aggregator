package io.github.italodgsilva.infra.retry

import io.github.italodgsilva.application.logger.Logger
import io.github.italodgsilva.domain.exception.TimeoutException
import io.netty.channel.ConnectTimeoutException
import io.vertx.core.impl.NoStackTraceTimeoutException
import jakarta.enterprise.context.ApplicationScoped
import kotlinx.coroutines.delay
import kotlin.reflect.KClass
import kotlin.time.Duration.Companion.milliseconds

@ApplicationScoped
class RetryExecutor(
    private val logger: Logger,
) {
    private val retriableExceptionsConverter =
        mapOf<KClass<out Throwable>, (Throwable) -> Throwable>(
            ConnectTimeoutException::class to { TimeoutException() },
            NoStackTraceTimeoutException::class to { TimeoutException() },
        )

    suspend fun <T> execute(
        maxAttempts: Int,
        retryDelay: Int,
        action: suspend () -> T,
    ): T {
        repeat(maxAttempts - 1) {
            runCatching {
                return action()
            }.onFailure { exception ->
                if (exception.isRetryable()) {
                    logger.warn("Operation timed out. Trying again after {} milliseconds.", retryDelay)
                    delay(retryDelay.milliseconds)
                }
            }
        }

        var lastException: Throwable? = null

        runCatching {
            return action()
        }.onFailure { exception ->
            lastException = exception.toDomainException()
        }

        throw lastException!!
    }

    private fun Throwable.isRetryable(): Boolean = retriableExceptionsConverter.keys.any { it.isInstance(this) }

    private fun Throwable.toDomainException(): Throwable =
        retriableExceptionsConverter.entries
            .firstOrNull { it.key.isInstance(this) }
            ?.value(this)
            ?: this
}
