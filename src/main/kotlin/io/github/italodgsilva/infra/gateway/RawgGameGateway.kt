package io.github.italodgsilva.infra.gateway

import io.github.italodgsilva.domain.entity.Game
import io.github.italodgsilva.domain.gateway.GameGateway

class RawgGameGateway : GameGateway {
    override suspend fun find(name: String): Game? {
        TODO("not yet implemented")
    }
}