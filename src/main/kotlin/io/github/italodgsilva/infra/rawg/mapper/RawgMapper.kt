package io.github.italodgsilva.infra.rawg.mapper

import io.github.italodgsilva.domain.entity.Game
import io.github.italodgsilva.infra.rawg.dto.RawgGameResponse
import kotlin.uuid.Uuid

object RawgMapper {
    fun toDomain(rawgGameResponse: RawgGameResponse): Game {
        return Game(
            uuid = Uuid.random(),
            name = rawgGameResponse.name,
            description = rawgGameResponse.description ?: "",
            genres = rawgGameResponse.genres.map { it.name }
        )
    }
}