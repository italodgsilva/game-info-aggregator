package io.github.italodgsilva.application.usecase.game.getgameinfobyname

import io.github.italodgsilva.application.usecase.shared.UseCaseOutput
import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class GetGameInfoByNameOutput(
    val name: String,
    val description: String,
    val genres: List<String> = emptyList()
) : UseCaseOutput