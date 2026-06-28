package io.github.italodgsilva.infra.repository

import io.github.italodgsilva.domain.repository.GameRepository

class GamePostgresRepository : GameRepository, PostgresRepository() {
}