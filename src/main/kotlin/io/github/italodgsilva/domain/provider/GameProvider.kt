package io.github.italodgsilva.domain.provider

import io.github.italodgsilva.domain.entity.Game

interface GameProvider : Provider {
    suspend fun find(name: String): List<Game>
}
