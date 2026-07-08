package io.github.italodgsilva.infra.rawg.gateway

import io.github.italodgsilva.domain.entity.Game
import io.github.italodgsilva.domain.exception.GameGatewayConnectionTimeoutException
import io.github.italodgsilva.domain.exception.GameGatewayResponseTimeoutException
import io.github.italodgsilva.domain.provider.GameProvider
import io.github.italodgsilva.infra.rawg.client.RawgApiClient
import io.github.italodgsilva.infra.rawg.mapper.RawgMapper
import io.netty.channel.ConnectTimeoutException
import io.vertx.core.impl.NoStackTraceTimeoutException
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class RawgGameGateway(
    @RestClient
    private val client: RawgApiClient,
    @ConfigProperty(name = "rawg.api-key")
    private val apiKey: String,
) : GameProvider {
    override suspend fun find(name: String): List<Game> {
        try {
            val response = client.search(name, this.apiKey)
            return response.results.map { RawgMapper.toDomain(it) }
        } catch (_: ConnectTimeoutException) {
            throw GameGatewayResponseTimeoutException("RAWG")
        } catch (_: NoStackTraceTimeoutException) {
            throw GameGatewayConnectionTimeoutException("RAWG")
        }
    }
}
