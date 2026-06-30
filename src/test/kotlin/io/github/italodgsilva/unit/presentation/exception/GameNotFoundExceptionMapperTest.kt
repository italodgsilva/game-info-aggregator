package io.github.italodgsilva.unit.presentation.exception

import io.github.italodgsilva.domain.exception.GameNotFoundException
import io.github.italodgsilva.presentation.exception.GameNotFoundExceptionMapper
import io.github.italodgsilva.presentation.response.error.ErrorResponse
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class GameNotFoundExceptionMapperTest {

    @Test
    fun `must map an exception to response`() {
        val exception = GameNotFoundException(gameName="Game Name")
        val response = GameNotFoundExceptionMapper().toResponse(exception)
        val responseEntity = response.entity as ErrorResponse
        assertEquals(Response.Status.NOT_FOUND.statusCode, response.status)
        assertEquals(Response.Status.NOT_FOUND.statusCode, responseEntity.status)
        assertEquals(exception.message, responseEntity.message)
        assertEquals(MediaType.APPLICATION_JSON_TYPE, response.mediaType)
    }

}