package io.github.italodgsilva.presentation.mapper

import io.github.italodgsilva.application.usecase.game.getgameinfobyname.GetGameInfoByNameInput
import io.github.italodgsilva.application.usecase.game.getgameinfobyname.GetGameInfoByNameOutput
import io.github.italodgsilva.application.usecase.game.getgameinfobyname.GetGameInfoByNameOutputElement
import io.github.italodgsilva.presentation.response.game.GetGameInfoByNameResponse
import io.github.italodgsilva.presentation.response.game.GetGameInfoByNameResponseElement

object GameResourceMapper {
    fun toInput(name: String) = GetGameInfoByNameInput(name)

    fun toResponse(output: GetGameInfoByNameOutput) =
        GetGameInfoByNameResponse(
            results = output.games.map { toResponseElement(it) },
        )

    private fun toResponseElement(outputElement: GetGameInfoByNameOutputElement) =
        GetGameInfoByNameResponseElement(
            name = outputElement.name,
            description = outputElement.description,
            genres = outputElement.genres,
        )
}
