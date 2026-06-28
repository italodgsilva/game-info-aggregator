package io.github.italodgsilva.domain.provider

import io.github.italodgsilva.domain.entity.Game

interface GameProvider {
    suspend fun findByName(name: String): Game?
}