package io.github.italodgsilva.unit.domain.entity

import io.github.italodgsilva.domain.entity.Game
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class GameTest {

    @Test
    fun `must create a game`() {
        val genres = listOf("adventure", "action", "FPS")
        val game = Game(UUID.randomUUID(), "Game", "description", genres)
        Assertions.assertEquals(genres.size, game.genres.size)
        Assertions.assertEquals(genres[0], game.genres[0])
        Assertions.assertEquals(game.description, "description")
        Assertions.assertEquals(game.name, "Game")
    }

}