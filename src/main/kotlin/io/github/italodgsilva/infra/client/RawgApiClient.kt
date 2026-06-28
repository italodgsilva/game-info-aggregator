package io.github.italodgsilva.infra.client

import io.github.italodgsilva.domain.entity.Game
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.QueryParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import kotlin.uuid.Uuid

data class RawgGenreResponse(
    val name: String
)

data class RawgGameResponse(
    val name: String,
    val description: String?,
    val genres: List<RawgGenreResponse>
)

data class RawgSearchResponse(
    val results: List<RawgGameResponse>
)

object RawgMapper {
    fun toDomain(rawgGameResponse: RawgGameResponse): Game {
        return Game(
            uuid = Uuid.random(),
            name = rawgGameResponse.name,
            description = rawgGameResponse.description ?: "",
            genres = rawgGameResponse.genres.map { it.name }
        )
    }
}

@RegisterRestClient(configKey = "rawg-api")
interface RawgApiClient {
    @GET
    @Path("/games")
    suspend fun search(
        @QueryParam("search") name: String,
        @QueryParam("key") apiKey: String,
    ): RawgSearchResponse
}