package io.github.italodgsilva.presentation.exception

import io.github.italodgsilva.presentation.response.error.ErrorResponse
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper

class GenericExceptionMapper : ExceptionMapper<Throwable> {
    override fun toResponse(exception: Throwable): Response {
        return Response.serverError()
            .entity(ErrorResponse("Internal server error."))
            .build()
    }
}