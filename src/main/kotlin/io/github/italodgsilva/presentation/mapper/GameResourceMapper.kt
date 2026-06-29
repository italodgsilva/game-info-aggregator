package io.github.italodgsilva.presentation.mapper

import io.github.italodgsilva.application.usecase.game.getgameinfobyname.GetGameInfoByNameInput
import io.github.italodgsilva.application.usecase.game.getgameinfobyname.GetGameInfoByNameOutput
import io.github.italodgsilva.presentation.response.game.GetGameInfoByNameResponse

object GameResourceMapper {

    fun toInput(
        name: String
    ) = GetGameInfoByNameInput(name)

    fun toResponse(
        output: GetGameInfoByNameOutput
    ) = GetGameInfoByNameResponse(
        name = output.name,
        description = output.description,
        genres = output.genres
    )
}