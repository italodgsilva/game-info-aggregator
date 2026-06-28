package io.github.italodgsilva.application.usecase.game

import io.github.italodgsilva.application.usecase.shared.UseCase
import io.github.italodgsilva.application.usecase.shared.UseCaseInput
import io.github.italodgsilva.application.usecase.shared.UseCaseOutput
import io.github.italodgsilva.domain.exception.GameNotFoundException
import io.github.italodgsilva.domain.provider.GameProvider

data class GetGameInfoByNameInput(
    val name: String
) : UseCaseInput

data class GetGameInfoByNameOutput(
    val name: String,
    val description: String,
    val genres: List<String>
) : UseCaseOutput

class GetGameInfoByNameUseCase (
    private val provider: GameProvider
): UseCase<GetGameInfoByNameInput, GetGameInfoByNameOutput> {
    override suspend fun execute(input: GetGameInfoByNameInput): GetGameInfoByNameOutput {
        val game = provider.findByName(input.name)
            ?: throw GameNotFoundException(gameName = input.name)
        return GetGameInfoByNameOutput(game.name, game.description, game.genres)
    }
}