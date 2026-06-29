package io.github.italodgsilva.infra.rawg.dto

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class RawgGameResponse(
    val name: String,
    val description: String?,
    val genres: List<RawgGenreResponse> = emptyList()
)