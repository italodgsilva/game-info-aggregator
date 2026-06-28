package io.github.italodgsilva.domain.exception

class InvalidGameNameException(
    val gameName: String
): DomainException(
    message = "Invalid game name: $gameName"
)