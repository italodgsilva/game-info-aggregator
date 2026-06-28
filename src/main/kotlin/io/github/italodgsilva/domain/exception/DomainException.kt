package io.github.italodgsilva.domain.exception

abstract class DomainException (
    override val message: String
) : RuntimeException(message)