package io.github.italodgsilva.domain.exception

class GameNotFoundException(
    gameName: String,
) : BaseException(
        message = "Game '$gameName' not found.",
    )
