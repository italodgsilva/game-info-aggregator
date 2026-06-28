package io.github.italodgsilva.domain.entity

import kotlin.uuid.Uuid

data class Game (
    override val uuid: Uuid,
    val name: String,
    val description: String,
    val genres: List<String>,
) : Entity {
}