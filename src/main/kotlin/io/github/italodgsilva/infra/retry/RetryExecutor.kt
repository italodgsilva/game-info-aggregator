package io.github.italodgsilva.infra.retry

import io.github.italodgsilva.application.logger.Logger
import jakarta.enterprise.context.ApplicationScoped
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@ApplicationScoped
class RetryExecutor(
    private val logger: Logger,
    private val retriableExceptionConverter: RetriableExceptionConverter,
) {
    @Suppress("TooGenericExceptionCaught")
    suspend fun <T> execute(
        maxAttempts: Int,
        retryDelay: Int,
        action: suspend () -> T,
    ): T {
        repeat(maxAttempts) {
            try {
                return action()
            } catch (exception: Exception) {
                if (retriableExceptionConverter.isRetryable(exception) && it < maxAttempts - 1) {
                    logger.warn("Operation timed out. Trying again after {} milliseconds.", retryDelay)
                    delay(retryDelay.milliseconds)
                } else {
                    throw retriableExceptionConverter.toDomainException(exception)
                }
            }
        }
        error("RetryExecutor reached an impossible state.")
    }
}
