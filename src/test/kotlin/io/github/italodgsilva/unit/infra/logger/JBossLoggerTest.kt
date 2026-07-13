package io.github.italodgsilva.unit.infra.logger

import io.github.italodgsilva.infra.logger.toMessageFormat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class JBossLoggerTest {
    @Test
    fun `should keep message unchanged when there are no placeholders`() {
        assertEquals(
            "Application started",
            "Application started".toMessageFormat(),
        )
    }

    @Test
    fun `should convert one placeholder`() {
        assertEquals(
            "Game {0}",
            "Game {}".toMessageFormat(),
        )
    }

    @Test
    fun `should convert multiple placeholders`() {
        assertEquals(
            "Game {0} loaded from {1}",
            "Game {} loaded from {}".toMessageFormat(),
        )
    }

    @Test
    fun `should convert sequential placeholders`() {
        assertEquals(
            "{0} {1} {2}",
            "{} {} {}".toMessageFormat(),
        )
    }

    @Test
    fun `should preserve surrounding text`() {
        assertEquals(
            "Error {0}: {1}",
            "Error {}: {}".toMessageFormat(),
        )
    }

    @Test
    fun `should return empty string`() {
        assertEquals(
            "",
            "".toMessageFormat(),
        )
    }
}
