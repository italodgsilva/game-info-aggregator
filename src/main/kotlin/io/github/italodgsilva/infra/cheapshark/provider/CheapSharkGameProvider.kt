package io.github.italodgsilva.infra.cheapshark.provider

import io.github.italodgsilva.domain.entity.Game
import io.github.italodgsilva.domain.provider.GameProvider
import io.github.italodgsilva.infra.cheapshark.gateway.CheapSharkGameGateway
import io.github.italodgsilva.infra.cheapshark.mapper.CheapSharkMapper
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class CheapSharkGameProvider(
    private val gateway: CheapSharkGameGateway,
) : GameProvider {
    override suspend fun find(name: String): List<Game> = gateway.findGame(name).map { CheapSharkMapper.toDomain(it) }
}
