package io.github.italodgsilva.infra.rawg.dto

data class RawgGameResponse(
    val name: String,
    val description: String?,
    val genres: List<RawgGenreResponse>
)