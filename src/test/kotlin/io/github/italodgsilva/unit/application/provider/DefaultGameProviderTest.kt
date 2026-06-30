package io.github.italodgsilva.unit.application.provider

import io.github.italodgsilva.application.provider.DefaultGameProvider
import io.github.italodgsilva.domain.entity.Game
import io.github.italodgsilva.domain.gateway.GameGateway
import io.github.italodgsilva.domain.repository.GameRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNull
import java.util.*

class DefaultGameProviderTest {

    private val repository = mockk<GameRepository>()
    private val gateway = mockk<GameGateway>()

    @Test
    fun `must provide a game by name when it found`() = runTest {

        val game = Game(
            uuid = UUID.randomUUID(),
            name = "Elden Ring",
            description = "Description of Elden Ring",
            genres = listOf("Action", "RPG")
        )

        // TODO Quando implementarmos o repositório, devemos definir o comportamento dele aqui
        coEvery {
            gateway.find(game.name)
        } returns game

        val provider = DefaultGameProvider(repository, gateway)
        val foundGame = provider.findByName(game.name)

        assertEquals(foundGame, game)

        coVerify(exactly = 1) {
            // TODO implementar para repository futuramente
            gateway.find(game.name)
        }

    }

    @Test
    fun `must return null when game is not found`() = runTest {
        val gameName = "Nonexistent Game"

        // TODO Quando implementarmos o repositório, devemos definir o comportamento dele aqui
        coEvery {
            gateway.find(gameName)
        } returns null

        val provider = DefaultGameProvider(repository, gateway)
        val foundGame = provider.findByName(gameName)

        assertNull(foundGame)

        coVerify(exactly = 1) {
            // TODO implementar para repository futuramente
            gateway.find(gameName)
        }

    }
}