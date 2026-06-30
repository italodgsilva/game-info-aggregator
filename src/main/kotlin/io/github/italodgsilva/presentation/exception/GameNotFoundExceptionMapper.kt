package io.github.italodgsilva.presentation.exception

import io.github.italodgsilva.domain.exception.GameNotFoundException
import io.github.italodgsilva.presentation.response.error.ErrorResponse

import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class GameNotFoundExceptionMapper : ExceptionMapper<GameNotFoundException> {
    override fun toResponse(exception: GameNotFoundException): Response =
        Response.status(Response.Status.NOT_FOUND)
            .entity(ErrorResponse(Response.Status.NOT_FOUND.statusCode, exception.message))
            .type(MediaType.APPLICATION_JSON)
            .build()
}