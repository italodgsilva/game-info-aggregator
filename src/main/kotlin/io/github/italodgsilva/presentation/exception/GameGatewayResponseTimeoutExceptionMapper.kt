package io.github.italodgsilva.presentation.exception

import io.github.italodgsilva.domain.exception.GameGatewayResponseTimeoutException
import io.github.italodgsilva.presentation.response.error.ErrorResponse
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class GameGatewayResponseTimeoutExceptionMapper : ExceptionMapper<GameGatewayResponseTimeoutException> {
    override fun toResponse(exception: GameGatewayResponseTimeoutException): Response =
        Response
            .status(Response.Status.GATEWAY_TIMEOUT)
            .entity(ErrorResponse(Response.Status.GATEWAY_TIMEOUT.statusCode, exception.message))
            .type(MediaType.APPLICATION_JSON)
            .build()
}
