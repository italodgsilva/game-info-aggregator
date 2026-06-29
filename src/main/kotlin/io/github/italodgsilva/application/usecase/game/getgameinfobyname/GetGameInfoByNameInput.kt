package io.github.italodgsilva.application.usecase.game.getgameinfobyname

import io.github.italodgsilva.application.usecase.shared.UseCaseInput

data class GetGameInfoByNameInput(
    val name: String
) : UseCaseInput