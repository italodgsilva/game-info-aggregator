package io.github.italodgsilva.infra.cheapshark.dto

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class CheapSharkGameResponse(
    val gameID: String,
    val steamAppID: String?,
    val cheapest: String,
    val cheapestDealID: String,
    val external: String,
)
