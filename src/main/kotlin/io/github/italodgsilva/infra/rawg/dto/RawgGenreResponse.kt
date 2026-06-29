package io.github.italodgsilva.infra.rawg.dto

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class RawgGenreResponse(
    val name: String
)
