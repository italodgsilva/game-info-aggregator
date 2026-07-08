package io.github.italodgsilva.integration.support

import io.github.italodgsilva.domain.entity.Game
import io.github.italodgsilva.domain.provider.GameProvider
import io.github.italodgsilva.support.factory.GameFactory
import io.quarkus.test.Mock
import jakarta.enterprise.context.ApplicationScoped

@Mock
@ApplicationScoped
class FakeGameProvider : GameProvider {
    override suspend fun find(name: String): List<Game> = listOf(GameFactory.create(name = name))
}
