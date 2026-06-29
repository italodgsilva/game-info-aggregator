package io.github.italodgsilva.infra.rawg.gateway

import io.github.italodgsilva.domain.entity.Game
import io.github.italodgsilva.domain.gateway.GameGateway
import io.github.italodgsilva.infra.rawg.client.RawgApiClient
import io.github.italodgsilva.infra.rawg.mapper.RawgMapper
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class RawgGameGateway(
    @RestClient
    private val client: RawgApiClient,

    @ConfigProperty(name = "rawg.api-key")
    private val apiKey: String

) : GameGateway {
    override suspend fun find(name: String): Game? {
        val response = client.search(name, this.apiKey)
        response.results.ifEmpty { return null }
        return RawgMapper.toDomain(response.results[0])
    }
}