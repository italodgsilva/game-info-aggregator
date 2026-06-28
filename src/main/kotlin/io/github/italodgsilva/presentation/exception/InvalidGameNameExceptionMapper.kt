package io.github.italodgsilva.presentation.exception

import io.github.italodgsilva.domain.exception.InvalidGameNameException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper

class InvalidGameNameExceptionMapper : ExceptionMapper<InvalidGameNameException> {
    override fun toResponse(exception: InvalidGameNameException): Response =
        Response.status(Response.Status.BAD_REQUEST)
            .entity(exception.message)
            .build()
}