package io.github.italodgsilva.support.factory

import io.github.serpro69.kfaker.Faker

object GameGenresListFactory {
    private val faker = Faker()

    private val availableGenres =
        listOf(
            "Action",
            "Adventure",
            "RPG",
            "Shooter",
            "Strategy",
            "Simulation",
            "Puzzle",
            "Sports",
            "Racing",
            "Indie",
        )

    fun generateGenres(size: Int = faker.random.nextInt(1, 5)): List<String> =
        availableGenres
            .shuffled()
            .take(size)
}
