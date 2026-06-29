package io.github.italodgsilva.infra.rawg.dto

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class RawgSearchResponse(
    val results: List<RawgGameResponse> = emptyList()
)