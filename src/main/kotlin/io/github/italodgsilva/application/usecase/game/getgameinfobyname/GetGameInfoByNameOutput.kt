package io.github.italodgsilva.application.usecase.game.getgameinfobyname

import io.github.italodgsilva.application.usecase.shared.UseCaseOutput

data class GetGameInfoByNameOutput(
    val name: String,
    val description: String,
    val genres: List<String>
) : UseCaseOutput