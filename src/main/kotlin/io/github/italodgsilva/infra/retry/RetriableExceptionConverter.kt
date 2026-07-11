package io.github.italodgsilva.infra.retry

import io.github.italodgsilva.domain.exception.TimeoutException
import io.netty.channel.ConnectTimeoutException
import io.vertx.core.impl.NoStackTraceTimeoutException
import jakarta.enterprise.context.ApplicationScoped
import kotlin.reflect.KClass

@ApplicationScoped
class RetriableExceptionConverter {
    private val retriableExceptionsMap =
        mapOf<KClass<out Throwable>, (Throwable) -> Throwable>(
            ConnectTimeoutException::class to { TimeoutException() },
            NoStackTraceTimeoutException::class to { TimeoutException() },
        )

    fun isRetryable(throwable: Throwable): Boolean =
        retriableExceptionsMap.keys.any {
            it.isInstance(throwable)
        }

    fun toDomainException(throwable: Throwable): Throwable =
        retriableExceptionsMap.entries
            .firstOrNull { it.key.isInstance(throwable) }
            ?.value(throwable)
            ?: throwable
}
