package io.github.italodgsilva.application.usecase.shared

interface UseCase<I: UseCaseInput, O: UseCaseOutput> {
    suspend fun execute(input: I): O
}