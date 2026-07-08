package io.github.italodgsilva.unit.presentation.exception

import io.github.italodgsilva.presentation.exception.GenericExceptionMapper
import io.github.italodgsilva.presentation.response.error.ErrorResponse
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GenericExceptionMapperTest {
    @Test
    fun `must map an exception to response`() {
        val exception = Exception("dummy")
        val response = GenericExceptionMapper().toResponse(exception)
        val responseEntity = response.entity as ErrorResponse
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.statusCode, response.status)
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.statusCode, responseEntity.status)
        assertEquals(exception.message, responseEntity.message)
        assertEquals(MediaType.APPLICATION_JSON_TYPE, response.mediaType)
    }
}
