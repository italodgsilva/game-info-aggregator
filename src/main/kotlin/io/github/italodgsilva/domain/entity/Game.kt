package io.github.italodgsilva.domain.entity

import java.util.*


data class Game (
    override val uuid: UUID,
    val name: String,
    val description: String,
    val genres: List<String>,
) : Entity {
}