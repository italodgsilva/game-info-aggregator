package io.github.italodgsilva.support.factory

import io.github.italodgsilva.infra.cheapshark.dto.CheapSharkGameResponse
import io.github.serpro69.kfaker.games.GamesFaker
import java.math.RoundingMode
import java.util.UUID
import kotlin.random.Random

object CheapSharkSearchResponseFactory {
    private val gamesFaker = GamesFaker()

    fun create(size: Int = 5): List<CheapSharkGameResponse> =
        List(size) {
            this.createSingleGame()
        }

    fun createSingleGame(): CheapSharkGameResponse =
        CheapSharkGameResponse(
            gameID = Random.nextInt().toString(),
            steamAppID = Random.nextInt().toString(),
            cheapest =
                Random
                    .nextFloat()
                    .toBigDecimal()
                    .setScale(2, RoundingMode.HALF_UP)
                    .toString(),
            cheapestDealID = UUID.randomUUID().toString(),
            external = gamesFaker.game.title(),
        )
}
