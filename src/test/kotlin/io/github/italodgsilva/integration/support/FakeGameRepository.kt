package io.github.italodgsilva.integration.support

import io.github.italodgsilva.domain.repository.GameRepository
import io.quarkus.test.Mock
import jakarta.enterprise.context.ApplicationScoped

@Mock
@ApplicationScoped
class FakeGameRepository : GameRepository {
}