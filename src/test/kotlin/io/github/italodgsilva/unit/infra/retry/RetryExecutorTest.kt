package io.github.italodgsilva.unit.infra.retry

import io.github.italodgsilva.application.logger.Logger
import io.github.italodgsilva.application.logger.LoggerFactory
import io.github.italodgsilva.domain.exception.TimeoutException
import io.github.italodgsilva.infra.retry.RetriableExceptionConverter
import io.github.italodgsilva.infra.retry.RetryExecutor
import io.github.serpro69.kfaker.Faker
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RetryExecutorTest {
    private val logger = mockk<Logger>(relaxed = true)
    private val loggerFactory = mockk<LoggerFactory>()
    private val faker = Faker()

    @Test
    fun `must retry on a retriable exception`() =
        runTest {
            coEvery { loggerFactory.getLogger(any()) } returns logger
            val action = mockk<suspend () -> Any>()
            val exception = TimeoutException()
            val converter = mockk<RetriableExceptionConverter>()

            coEvery { action() } throws exception
            coEvery { converter.isRetryable(exception) } returns true
            coEvery { converter.toDomainException(exception) } returns exception
            val maxAttempts = faker.random.nextInt(2, 7)
            val retryDelay = faker.random.nextInt(100, 1000)

            val retryExecutor = RetryExecutor(loggerFactory, converter)

            assertThrows<TimeoutException> {
                retryExecutor.execute(maxAttempts, retryDelay, action)
            }

            coVerify(exactly = maxAttempts) {
                action()
            }

            coVerify(exactly = maxAttempts - 1) {
                logger.warn(any(), any())
            }
        }

    @Test
    fun `must not retry on a non-retriable exception`() =
        runTest {
            coEvery { loggerFactory.getLogger(any()) } returns logger
            val action = mockk<suspend () -> Any>()
            val exception = Exception()
            val converter = mockk<RetriableExceptionConverter>()

            coEvery { action() } throws exception
            coEvery { converter.isRetryable(exception) } returns false
            coEvery { converter.toDomainException(exception) } returns exception
            val maxAttempts = faker.random.nextInt(2, 7)
            val retryDelay = faker.random.nextInt(100, 1000)

            val retryExecutor = RetryExecutor(loggerFactory, converter)

            assertThrows<Exception> {
                retryExecutor.execute(maxAttempts, retryDelay, action)
            }

            coVerify(exactly = 1) {
                action()
            }
        }
}
