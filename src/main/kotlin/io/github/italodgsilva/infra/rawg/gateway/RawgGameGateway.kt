package io.github.italodgsilva.infra.rawg.gateway

import io.github.italodgsilva.application.logger.Logger
import io.github.italodgsilva.domain.entity.Game
import io.github.italodgsilva.domain.exception.GameGatewayConnectionTimeoutException
import io.github.italodgsilva.domain.exception.GameGatewayResponseTimeoutException
import io.github.italodgsilva.domain.provider.GameProvider
import io.github.italodgsilva.infra.rawg.client.RawgApiClient
import io.github.italodgsilva.infra.rawg.mapper.RawgMapper
import io.github.italodgsilva.infra.retry.RetryExecutor
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
            } catch (_: ConnectTimeoutException) {
                logger.error("Could not connect to RAWG API.")
                throw GameGatewayResponseTimeoutException("RAWG")
            } catch (_: NoStackTraceTimeoutException) {
                logger.error("Could not receive response from RAWG API.")
                throw GameGatewayConnectionTimeoutException("RAWG")
            }
        return response.results.map { RawgMapper.toDomain(it) }
    }
}
