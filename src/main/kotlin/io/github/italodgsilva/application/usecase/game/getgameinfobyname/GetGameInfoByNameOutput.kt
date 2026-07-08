package io.github.italodgsilva.application.usecase.game.getgameinfobyname

import io.github.italodgsilva.application.usecase.shared.UseCaseOutput

data class GetGameInfoByNameOutput(
    val games: List<GetGameInfoByNameOutputElement>,
) : UseCaseOutput
