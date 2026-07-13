package io.github.italodgsilva.infra.cheapshark.mapper

import io.github.italodgsilva.domain.entity.Game
import io.github.italodgsilva.infra.cheapshark.dto.CheapSharkGameResponse
import java.util.UUID

object CheapSharkMapper {
    fun toDomain(response: CheapSharkGameResponse): Game =
        Game(
            uuid = UUID.randomUUID(),
            name = response.external,
            description = "",
            genres = emptyList(),
            cheapest = response.cheapest.toFloat(),
        )
}
