package io.github.italodgsilva.unit.presentation.exception

import io.github.italodgsilva.domain.exception.GameGatewayResponseTimeoutException
import io.github.italodgsilva.presentation.exception.GameGatewayResponseTimeoutExceptionMapper
import io.github.italodgsilva.presentation.response.error.ErrorResponse
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameGatewayResponseTimeoutExceptionMapperTest {
    @Test
    fun `must map an exception to response`() {
        val exception = GameGatewayResponseTimeoutException(gateway = "test")
        val response = GameGatewayResponseTimeoutExceptionMapper().toResponse(exception)
        val responseEntity = response.entity as ErrorResponse
        assertEquals(Response.Status.GATEWAY_TIMEOUT.statusCode, response.status)
        assertEquals(Response.Status.GATEWAY_TIMEOUT.statusCode, responseEntity.status)
        assertEquals(exception.message, responseEntity.message)
        assertEquals(MediaType.APPLICATION_JSON_TYPE, response.mediaType)
    }
}
