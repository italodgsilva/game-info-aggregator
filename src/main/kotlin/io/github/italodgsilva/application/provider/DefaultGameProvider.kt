package io.github.italodgsilva.application.provider

import io.github.italodgsilva.domain.entity.Game
import io.github.italodgsilva.domain.gateway.GameGateway
import io.github.italodgsilva.domain.provider.GameProvider
import io.github.italodgsilva.domain.repository.GameRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class DefaultGameProvider(
    private val repository: GameRepository,
    private val gateway: GameGateway
) : GameProvider {

    override suspend fun findByName(name: String): Game? {
        // TODO melhorar na segunda fase.
        val game = gateway.find(name)
        return game
    }

}