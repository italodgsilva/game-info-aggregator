package io.github.italodgsilva.support.factory

import io.github.italodgsilva.domain.entity.Game
import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.games.GamesFaker
import java.util.*

object GameFactory {

    private val faker = Faker()
    private val gamesFaker = GamesFaker()

    private val availableGenres = listOf(
        "Action",
        "Adventure",
        "RPG",
        "Shooter",
        "Strategy",
        "Simulation",
        "Puzzle",
        "Sports",
        "Racing",
        "Indie"
    )

    fun create(
        uuid: UUID = UUID.randomUUID(),
        name: String = gamesFaker.game.title(),
        description: String = "Description ${faker.random.nextUUID()}",
        genres: List<String> = randomGenres()
    ) =  Game(
        uuid = uuid,
        name = name,
        description = description,
        genres = genres
    )

    private fun randomGenres(): List<String> {
        val size = faker.random.nextInt(1, 5)
        return availableGenres
            .shuffled()
            .take(size)
    }

}