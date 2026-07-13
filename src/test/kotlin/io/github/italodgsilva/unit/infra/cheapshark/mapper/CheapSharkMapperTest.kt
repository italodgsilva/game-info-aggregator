package io.github.italodgsilva.unit.infra.cheapshark.mapper

import io.github.italodgsilva.infra.cheapshark.mapper.CheapSharkMapper
import io.github.italodgsilva.support.factory.CheapSharkSearchResponseFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CheapSharkMapperTest {
    @Test
    fun `must convert a cheapshark provider result to domain`() {
        val result = CheapSharkSearchResponseFactory.createSingleGame()
        val game = CheapSharkMapper.toDomain(result)
        assertEquals(result.external, game.name)
        assertEquals(result.cheapest.toFloat(), game.cheapest)
    }
}
