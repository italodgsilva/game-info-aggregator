package io.github.italodgsilva.domain.exception

class GameGatewayConnectionTimeoutException(
    gateway: String,
) : BaseException(
        message = "Game gateway '$gateway' did not connected in time.",
    )
