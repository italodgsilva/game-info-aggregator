package io.github.italodgsilva.presentation.exception

import io.github.italodgsilva.presentation.response.error.ErrorResponse
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class GenericExceptionMapper : ExceptionMapper<Throwable> {
    override fun toResponse(exception: Throwable): Response = Response
        .serverError()
        .entity(ErrorResponse(Response.Status.INTERNAL_SERVER_ERROR.statusCode, "Internal server error."))
        .type(MediaType.APPLICATION_JSON)
        .build()
}