package io.github.italodgsilva.domain.exception

class GameNotFoundException(
    gameName: String
) : DomainException(
    message = "Game '$gameName' not found.",
)