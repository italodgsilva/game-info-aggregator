package io.github.italodgsilva.unit.infra.cheapshark.gateway

import io.github.italodgsilva.application.logger.Logger
import io.github.italodgsilva.application.logger.LoggerFactory
import io.github.italodgsilva.domain.exception.GameGatewayTimeoutException
import io.github.italodgsilva.domain.exception.TimeoutException
import io.github.italodgsilva.infra.cheapshark.client.CheapSharkApiClient
import io.github.italodgsilva.infra.cheapshark.gateway.CheapSharkGameGateway
import io.github.italodgsilva.infra.retry.RetriableExceptionConverter
import io.github.italodgsilva.infra.retry.RetryExecutor
import io.github.italodgsilva.support.factory.CheapSharkSearchResponseFactory
import io.github.serpro69.kfaker.games.GamesFaker
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test

class CheapSharkGameGatewayTest {
    private val client = mockk<CheapSharkApiClient>()
    private val logger = mockk<Logger>(relaxed = true)
    private val loggerFactory = mockk<LoggerFactory>()
    private val gamesFaker = GamesFaker()

    @Test
    fun `must find a game from cheapshark successfully`() =
        runTest {
            coEvery { loggerFactory.getLogger(any()) } returns logger
            val gameName = gamesFaker.game.title()
            val retriableExceptionConverter = mockk<RetriableExceptionConverter>()
            val retryExecutor = RetryExecutor(loggerFactory, retriableExceptionConverter)
            val response = CheapSharkSearchResponseFactory.create(5)

            coEvery {
                client.searchGame(gameName)
            } returns response

            val gateway =
                CheapSharkGameGateway(
                    retryExecutor = retryExecutor,
                    client = client,
                    loggerFactory = loggerFactory,
                    maxAttempts = 3,
                    retryDelay = 1000,
                )

            val games = gateway.findGame(gameName)

            coVerify(exactly = 1) { client.searchGame(gameName) }
            assertEquals(response.size, games.size)
        }

    @Test
    fun `must throw a gateway timeout error`() =
        runTest {
            coEvery { loggerFactory.getLogger(any()) } returns logger
            val gameName = gamesFaker.game.title()
            val retryExecutor = mockk<RetryExecutor>()
            coEvery {
                retryExecutor.execute(any(), any(), any<suspend () -> Any>())
            } throws TimeoutException()
            val gateway =
                CheapSharkGameGateway(
                    retryExecutor = retryExecutor,
                    client = client,
                    loggerFactory = loggerFactory,
                    maxAttempts = 3,
                    retryDelay = 1000,
                )
            try {
                gateway.findGame(gameName)
                fail("gateway should throw a gateway timeout")
            } catch (_: GameGatewayTimeoutException) {
                assert(true)
            }
        }
}
