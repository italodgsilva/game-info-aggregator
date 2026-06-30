package io.github.italodgsilva.unit.application.usecase

import io.github.italodgsilva.application.usecase.game.getgameinfobyname.GetGameInfoByNameInput
import io.github.italodgsilva.application.usecase.game.getgameinfobyname.GetGameInfoByNameUseCase
import io.github.italodgsilva.domain.exception.GameNotFoundException
import io.github.italodgsilva.domain.provider.GameProvider
import io.github.italodgsilva.support.factory.GameFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GetGameInfoByNameUseCaseTest {

    private val provider = mockk<GameProvider>()
    private val useCase = GetGameInfoByNameUseCase(provider)

    @Test
    fun `must return a game info when it is found`() = runTest {

        val game = GameFactory.create()
        coEvery {
            provider.findByName(game.name)
        } returns game

        val output = useCase.execute(GetGameInfoByNameInput(game.name))

        assertEquals(game.name, output.name)
        assertEquals(game.description, output.description)
        assertEquals(game.genres, output.genres)

        coVerify(exactly = 1) {
            provider.findByName(game.name)
        }
    }

    @Test
    fun `must throws an exception when the game is not found`() = runTest {

        val gameName = "Non existing game"

        coEvery {
            provider.findByName(gameName)
        } returns null

        assertThrows<GameNotFoundException> {
            runBlocking {
                useCase.execute(GetGameInfoByNameInput(gameName))
            }
        }

        coVerify(exactly = 1) {
            provider.findByName(gameName)
        }
    }

}