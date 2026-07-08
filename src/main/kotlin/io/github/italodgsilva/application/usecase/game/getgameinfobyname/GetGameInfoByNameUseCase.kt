package io.github.italodgsilva.application.usecase.game.getgameinfobyname

import io.github.italodgsilva.application.registry.Registry
import io.github.italodgsilva.application.usecase.shared.UseCase
import io.github.italodgsilva.domain.entity.Game
import io.github.italodgsilva.domain.exception.GameNotFoundException
import io.github.italodgsilva.domain.provider.GameProvider
import io.github.italodgsilva.domain.service.GameAggregationService
import jakarta.enterprise.context.ApplicationScoped
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

@ApplicationScoped
class GetGameInfoByNameUseCase(
    private val gameProviderRegistry: Registry<GameProvider>,
    private val aggregationService: GameAggregationService,
) : UseCase<GetGameInfoByNameInput, GetGameInfoByNameOutput> {
    override suspend fun execute(input: GetGameInfoByNameInput): GetGameInfoByNameOutput {
        val providers = gameProviderRegistry.all()
        val games: List<Game> =
            coroutineScope {
                providers
                    .map { async { it.find(input.name) } }
                    .awaitAll()
                    .flatten()
            }
        if (games.isEmpty()) {
            throw GameNotFoundException(gameName = input.name)
        }

        val aggregatedGameInfos = aggregationService.aggregate(games)

        return GetGameInfoByNameOutput(
            aggregatedGameInfos.map { this.toOutput(it) },
        )
    }

    private fun toOutput(game: Game): GetGameInfoByNameOutputElement =
        GetGameInfoByNameOutputElement(game.name, game.description, game.genres)
}
