package io.github.italodgsilva.integration.support

import io.github.italodgsilva.domain.entity.Game
import io.github.italodgsilva.domain.gateway.GameGateway
import io.github.italodgsilva.support.factory.GameFactory
import io.quarkus.test.Mock
import jakarta.enterprise.context.ApplicationScoped

@Mock
@ApplicationScoped
class FakeGameGateway : GameGateway {
    override suspend fun find(name: String): Game? =
        GameFactory.create(name = name)
}