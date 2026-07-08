package io.github.italodgsilva.domain.exception

class GameGatewayResponseTimeoutException(
    gateway: String,
) : BaseException(
        message = "Game gateway '$gateway' did not respond in time.",
    )
