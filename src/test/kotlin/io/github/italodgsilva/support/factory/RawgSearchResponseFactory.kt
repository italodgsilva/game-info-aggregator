package io.github.italodgsilva.support.factory

import io.github.italodgsilva.infra.rawg.dto.RawgGameResponse
import io.github.italodgsilva.infra.rawg.dto.RawgGenreResponse
import io.github.italodgsilva.infra.rawg.dto.RawgSearchResponse
import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.games.GamesFaker

object RawgSearchResponseFactory {
    private val faker = Faker()
    private val gamesFaker = GamesFaker()

    fun create(size: Int = 5): RawgSearchResponse {
        val games =
            List(size) {
                this.createSingleGame()
            }
        return RawgSearchResponse(results = games)
    }

    fun createSingleGame(): RawgGameResponse {
        val name = gamesFaker.game.title()
        val description = "Description ${faker.random.nextUUID()}"
        val rawgGenreResponse: List<RawgGenreResponse> =
            GameGenresListFactory
                .generateGenres()
                .map {
                    RawgGenreResponse(it)
                }
        return RawgGameResponse(
            name = name,
            description = description,
            genres = rawgGenreResponse,
        )
    }
}
