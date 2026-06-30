package io.github.italodgsilva.unit.infra.rawg.mapper

import io.github.italodgsilva.infra.rawg.dto.RawgGameResponse
import io.github.italodgsilva.infra.rawg.dto.RawgGenreResponse
import io.github.italodgsilva.infra.rawg.mapper.RawgMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RawgMapperTest {

    @Test
    fun `must map a rawg response to a game entity`() {

        val genres = listOf("action", "adventure")

        val rawgGameResponse = RawgGameResponse(
            name = "Elden Ring",
            description = "Description of Elden Ring",
            genres = listOf(
                RawgGenreResponse(name = genres[0]),
                RawgGenreResponse(name = genres[1])
            )
        )

        val game = RawgMapper.toDomain(rawgGameResponse)

        assertEquals(rawgGameResponse.name, game.name)
        assertEquals(rawgGameResponse.description, game.description)
        assertEquals(game.genres, genres)
    }

    @Test
    fun `must map a rawg response to a game entity without description`() {
        val genres = listOf("action", "adventure")

        val rawgGameResponse = RawgGameResponse(
            name = "Elden Ring",
            description = null,
            genres = listOf(
                RawgGenreResponse(genres[0]),
                RawgGenreResponse(genres[1])
            )
        )

        val game = RawgMapper.toDomain(rawgGameResponse)

        assertEquals(rawgGameResponse.name, game.name)
        assertEquals(game.description, "")
        assertEquals(game.genres, genres)
    }

    @Test
    fun `must map a rawg response to a game entity without genres`() {

        val rawgGameResponse = RawgGameResponse(
            name = "Elden Ring",
            description = "Description of Elden Ring",
            genres = listOf<RawgGenreResponse>()
        )

        val game = RawgMapper.toDomain(rawgGameResponse)

        assertEquals(rawgGameResponse.name, game.name)
        assertEquals(rawgGameResponse.description, game.description)
        assertEquals(game.genres, listOf<String>())
    }
}