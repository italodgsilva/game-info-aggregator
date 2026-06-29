package io.github.italodgsilva.presentation.response.game

import io.github.italodgsilva.presentation.response.shared.Response
import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class GetGameInfoByNameResponse(
    val name: String,
    val description: String,
    val genres: List<String> = emptyList()
) : Response