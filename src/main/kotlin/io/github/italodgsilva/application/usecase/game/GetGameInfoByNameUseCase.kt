package io.github.italodgsilva.application.usecase.game

import io.github.italodgsilva.application.usecase.shared.UseCase
import io.github.italodgsilva.application.usecase.shared.UseCaseInput
import io.github.italodgsilva.application.usecase.shared.UseCaseOutput

data class GetGameInfoByNameInput(
    val name: String
) : UseCaseInput

data class GetGameInfoByNameOutput(
    val name: String,
    val description: String,
    val genres: List<String>
) : UseCaseOutput

class GetGameInfoByNameUseCase : UseCase {
    override fun execute(input: UseCaseInput): UseCaseOutput {
        return GetGameInfoByNameOutput("", "", listOf("", ""))
    }
}