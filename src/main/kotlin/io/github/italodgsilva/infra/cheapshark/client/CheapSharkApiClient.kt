package io.github.italodgsilva.infra.cheapshark.client

import io.github.italodgsilva.infra.cheapshark.dto.CheapSharkGameResponse
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.QueryParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(configKey = "cheapshark-api")
interface CheapSharkApiClient {
    @GET
    @Path("/games")
    suspend fun searchGame(
        @QueryParam("title") title: String,
    ): List<CheapSharkGameResponse>
}
