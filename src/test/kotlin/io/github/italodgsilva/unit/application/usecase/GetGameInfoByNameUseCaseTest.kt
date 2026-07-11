package io.github.italodgsilva.unit.application.usecase

import io.github.italodgsilva.application.registry.Registry
import io.github.italodgsilva.application.usecase.game.getgameinfobyname.GetGameInfoByNameInput
import io.github.italodgsilva.application.usecase.game.getgameinfobyname.GetGameInfoByNameUseCase
import io.github.italodgsilva.domain.exception.GameNotFoundException
import io.github.italodgsilva.domain.provider.GameProvider
import io.github.italodgsilva.domain.service.GameAggregationService
import io.github.italodgsilva.support.factory.GameFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GetGameInfoByNameUseCaseTest {
    private val gameProviderRegistry = mockk<Registry<GameProvider>>()
    private val aggregationService = mockk<GameAggregationService>()
    private val useCase = GetGameInfoByNameUseCase(gameProviderRegistry, aggregationService)

    @Test
    fun `must return a game info when only one game is found`() =
        runTest {
            val provider = mockk<GameProvider>()
            val game = GameFactory.create()
            val results = listOf(game)
            coEvery {
                provider.find(game.name)
            } returns results
            coEvery {
                gameProviderRegistry.all()
            } returns listOf(provider)
            coEvery {
                aggregationService.aggregate(results)
            } returns results

            val output = useCase.execute(GetGameInfoByNameInput(game.name))

            assertEquals(game.name, output.games[0].name)
            assertEquals(game.description, output.games[0].description)
            assertEquals(game.genres, output.games[0].genres)

            coVerify(exactly = 1) {
                provider.find(game.name)
            }
        }

    @Test
    fun `must throws an exception when the game is not found`() =
        runTest {
            val gameName = "Non existing game"
            val provider = mockk<GameProvider>()
            coEvery {
                provider.find(gameName)
            } returns emptyList()
            coEvery {
                gameProviderRegistry.all()
            } returns listOf(provider)

            assertThrows<GameNotFoundException> {
                useCase.execute(GetGameInfoByNameInput(gameName))
            }

            coVerify(exactly = 1) {
                provider.find(gameName)
            }
        }
}
