package io.github.italodgsilva.infra.retry

import io.github.italodgsilva.application.logger.Logger
import io.netty.channel.ConnectTimeoutException
import io.vertx.core.impl.NoStackTraceTimeoutException
import jakarta.enterprise.context.ApplicationScoped
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@ApplicationScoped
class RetryExecutor(
    private val logger: Logger,
) {
    suspend fun <T> execute(
        maxAttempts: Int,
        retryDelay: Int,
        action: suspend () -> T,
    ): T {
        repeat(maxAttempts - 1) {
            try {
                return action()
            } catch (_: ConnectTimeoutException) {
                logger.warn("Connection timeout. Trying again after {} milliseconds.", retryDelay)
                delay(retryDelay.milliseconds)
            } catch (_: NoStackTraceTimeoutException) {
                logger.warn("Reading timeout. Trying again after {} milliseconds.", retryDelay)
                delay(retryDelay.milliseconds)
            }
        }
        return action()
    }
}
