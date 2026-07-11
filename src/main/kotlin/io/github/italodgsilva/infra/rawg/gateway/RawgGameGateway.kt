package io.github.italodgsilva.infra.rawg.gateway

import io.github.italodgsilva.application.logger.Logger
import io.github.italodgsilva.domain.entity.Game
import io.github.italodgsilva.domain.exception.GameGatewayTimeoutException
import io.github.italodgsilva.domain.exception.TimeoutException
import io.github.italodgsilva.domain.provider.GameProvider
import io.github.italodgsilva.infra.rawg.client.RawgApiClient
import io.github.italodgsilva.infra.rawg.mapper.RawgMapper
import io.github.italodgsilva.infra.retry.RetryExecutor
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class RawgGameGateway(
    @RestClient
    private val client: RawgApiClient,
    @ConfigProperty(name = "rawg.api-key")
    private val apiKey: String,
    @ConfigProperty(name = "rawg.max-attempts")
    private val maxAttempts: Int,
    @ConfigProperty(name = "rawg.retry-delay")
    private val retryDelay: Int,
    private val retryExecutor: RetryExecutor,
    private val logger: Logger,
) : GameProvider {
    override suspend fun find(name: String): List<Game> {
        val response =
            try {
                retryExecutor.execute(
                    maxAttempts = maxAttempts,
                    retryDelay = retryDelay,
                ) {
                    client.search(name, this.apiKey)
                }
            } catch (_: TimeoutException) {
                logger.error("Could not connect to RAWG API.")
                throw GameGatewayTimeoutException("RAWG")
            }
        return response.results.map { RawgMapper.toDomain(it) }
    }
}
