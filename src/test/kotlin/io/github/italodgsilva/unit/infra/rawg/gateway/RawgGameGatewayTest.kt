package io.github.italodgsilva.unit.infra.rawg.gateway

import io.github.italodgsilva.application.logger.Logger
import io.github.italodgsilva.domain.exception.GameGatewayTimeoutException
import io.github.italodgsilva.domain.exception.TimeoutException
import io.github.italodgsilva.infra.rawg.client.RawgApiClient
import io.github.italodgsilva.infra.rawg.gateway.RawgGameGateway
import io.github.italodgsilva.infra.retry.RetryExecutor
import io.github.italodgsilva.support.factory.RawgSearchResponseFactory
import io.github.serpro69.kfaker.games.GamesFaker
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test

class RawgGameGatewayTest {
    private val client = mockk<RawgApiClient>()
    private val logger = mockk<Logger>(relaxed = true)
    private val gamesFaker = GamesFaker()
    private val apiKey = "test-api-key"

    @Test
    fun `must find a game from rawg successfully`() =
        runTest {
            val gameName = gamesFaker.game.title()
            val response = RawgSearchResponseFactory.create()
            val retryExecutor = RetryExecutor(logger)

            coEvery {
                client.search(gameName, apiKey)
            } returns response

            val gateway =
                RawgGameGateway(
                    retryExecutor = retryExecutor,
                    client = client,
                    logger = logger,
                    apiKey = apiKey,
                    maxAttempts = 3,
                    retryDelay = 1000,
                )

            val games = gateway.find(gameName)

            coVerify(exactly = 1) { client.search(gameName, apiKey) }
            assertEquals(response.results.size, games.size)
        }

    @Test
    fun `must throw a gateway timeout error`() =
        runTest {
            val gameName = gamesFaker.game.title()
            val retryExecutor = mockk<RetryExecutor>()
            coEvery {
                retryExecutor.execute(any(), any(), any<suspend () -> Any>())
            } throws TimeoutException()
            val gateway =
                RawgGameGateway(
                    retryExecutor = retryExecutor,
                    client = client,
                    logger = logger,
                    apiKey = apiKey,
                    maxAttempts = 3,
                    retryDelay = 1000,
                )
            try {
                gateway.find(gameName)
                fail("gateway should throw a gateway timeout")
            } catch (_: GameGatewayTimeoutException) {
                assert(true)
            }
        }
}
