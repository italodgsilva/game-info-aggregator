package io.github.italodgsilva.infra.repository

import io.github.italodgsilva.domain.repository.GameRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class GamePostgresRepository : GameRepository, PostgresRepository() {
}