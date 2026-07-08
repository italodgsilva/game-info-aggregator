package io.github.italodgsilva.domain.exception

abstract class BaseException(
    override val message: String,
) : Exception(message)
