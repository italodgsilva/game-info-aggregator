package io.github.italodgsilva.application.registry

interface Registry<T> {
    fun all(): Collection<T>
}
