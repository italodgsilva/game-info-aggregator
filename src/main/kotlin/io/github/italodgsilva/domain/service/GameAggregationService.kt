package io.github.italodgsilva.domain.service

import io.github.italodgsilva.domain.entity.Game
import jakarta.enterprise.context.ApplicationScoped

// TODO Deixar o DomainService livre de tecnologias

@ApplicationScoped
class GameAggregationService : DomainService {
    fun aggregate(games: Collection<Game>): Collection<Game> =
        games
            .groupBy { it.name }
            .mapValues { (_, gameInfo) ->
                val first = gameInfo.first()
                Game(
                    uuid = first.uuid,
                    name = first.name,
                    description = gameInfo.joinToString(". ") { it.description },
                    genres = gameInfo.flatMap { it.genres }.distinct(),
                )
            }.values
}
