package io.github.italodgsilva.application.usecase.shared

interface UseCase {
    fun execute(input: UseCaseInput): UseCaseOutput
}