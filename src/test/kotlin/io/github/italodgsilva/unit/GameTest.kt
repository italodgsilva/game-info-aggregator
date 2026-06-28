package io.github.italodgsilva.unit

import io.github.italodgsilva.domain.entity.Game
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.uuid.Uuid

class GameTest {

    @Test
    fun `must create a game`() {
        val genres = listOf("adventure", "action", "FPS")
        val game = Game(Uuid.random(), "Game", "description", genres)
        assertEquals(genres.size, game.genres.size)
        assertEquals(genres[0], game.genres[0])
        assertEquals(game.description, "description")
        assertEquals(game.name, "Game")
    }

}