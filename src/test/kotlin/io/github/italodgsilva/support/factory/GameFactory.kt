package io.github.italodgsilva.support.factory

import io.github.italodgsilva.domain.entity.Game
import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.games.GamesFaker
import java.util.UUID

object GameFactory {
    private val faker = Faker()
    private val gamesFaker = GamesFaker()

    fun create(
        uuid: UUID = UUID.randomUUID(),
        name: String = gamesFaker.game.title(),
        description: String = "Description ${faker.random.nextUUID()}",
        genres: List<String> = GameGenresListFactory.generateGenres(),
    ) = Game(
        uuid = uuid,
        name = name,
        description = description,
        genres = genres,
    )
}
