package io.github.italodgsilva.unit.infra.retry

import io.github.italodgsilva.domain.exception.TimeoutException
import io.github.italodgsilva.infra.retry.RetriableExceptionConverter
import io.netty.channel.ConnectTimeoutException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RetriableExceptionConverterTest {
    @Test
    fun `must convert a timeout exception to domain timeout exception`() {
        val converter = RetriableExceptionConverter()
        val exception = ConnectTimeoutException()
        val converted = converter.toDomainException(exception)
        assertEquals(TimeoutException::class, converted::class)
    }

    @Test
    fun `must convert a non-retriable exception to itself`() {
        val converter = RetriableExceptionConverter()
        val exception = Exception()
        val converted = converter.toDomainException(exception)
        assertEquals(exception, converted)
    }

    @Test
    fun `must return true if an exception is retriable`() {
        val converter = RetriableExceptionConverter()
        val exception = ConnectTimeoutException()
        assert(converter.isRetryable(exception))
    }

    @Test
    fun `must return false if an exception is non-retriable`() {
        val converter = RetriableExceptionConverter()
        val exception = Exception()
        assert(!converter.isRetryable(exception))
    }
}
