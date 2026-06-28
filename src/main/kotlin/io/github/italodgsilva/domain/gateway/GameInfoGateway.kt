package io.github.italodgsilva.domain.gateway

import io.github.italodgsilva.domain.entity.Game

interface GameInfoGateway {
    suspend fun find(name: String): Game?
}