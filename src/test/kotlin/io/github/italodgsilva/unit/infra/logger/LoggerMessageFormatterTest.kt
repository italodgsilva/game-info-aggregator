package io.github.italodgsilva.unit.infra.logger

import io.github.italodgsilva.infra.logger.LoggerMessageFormatter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LoggerMessageFormatterTest {
    @Test
    fun `should keep message unchanged when there are no placeholders`() {
        assertEquals(
            "Application started",
            LoggerMessageFormatter.format("Application started"),
        )
    }

    @Test
    fun `should convert one placeholder`() {
        assertEquals(
            "Game {0}",
            LoggerMessageFormatter.format("Game {}"),
        )
    }

    @Test
    fun `should convert multiple placeholders`() {
        assertEquals(
            "Game {0} loaded from {1}",
            LoggerMessageFormatter.format("Game {} loaded from {}"),
        )
    }

    @Test
    fun `should convert sequential placeholders`() {
        assertEquals(
            "{0} {1} {2}",
            LoggerMessageFormatter.format("{} {} {}"),
        )
    }

    @Test
    fun `should preserve surrounding text`() {
        assertEquals(
            "Error {0}: {1}",
            LoggerMessageFormatter.format("Error {}: {}"),
        )
    }

    @Test
    fun `should return empty string`() {
        assertEquals(
            "",
            LoggerMessageFormatter.format(""),
        )
    }
}
