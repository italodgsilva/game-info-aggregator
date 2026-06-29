package io.github.italodgsilva.application.usecase.game.getgameinfobyname

import io.github.italodgsilva.application.usecase.shared.UseCase
import io.github.italodgsilva.domain.exception.GameNotFoundException
import io.github.italodgsilva.domain.provider.GameProvider
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class GetGameInfoByNameUseCase (
    private val provider: GameProvider
): UseCase<GetGameInfoByNameInput, GetGameInfoByNameOutput> {
    override suspend fun execute(input: GetGameInfoByNameInput): GetGameInfoByNameOutput {
        val game = provider.findByName(input.name)
            ?: throw GameNotFoundException(gameName = input.name)
        return GetGameInfoByNameOutput(game.name, game.description, game.genres)
    }
}