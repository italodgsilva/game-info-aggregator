package io.github.italodgsilva.unit.presentation.exception

import io.github.italodgsilva.domain.exception.GameGatewayTimeoutException
import io.github.italodgsilva.presentation.exception.GameGatewayTimeoutExceptionMapper
import io.github.italodgsilva.presentation.response.error.ErrorResponse
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameGatewayTimeoutExceptionMapperTest {
    @Test
    fun `must map an exception to response`() {
        val exception = GameGatewayTimeoutException(gateway = "test")
        val response = GameGatewayTimeoutExceptionMapper().toResponse(exception)
        val responseEntity = response.entity as ErrorResponse
        assertEquals(Response.Status.SERVICE_UNAVAILABLE.statusCode, response.status)
        assertEquals(Response.Status.SERVICE_UNAVAILABLE.statusCode, responseEntity.status)
        assertEquals(exception.message, responseEntity.message)
        assertEquals(MediaType.APPLICATION_JSON_TYPE, response.mediaType)
    }
}
