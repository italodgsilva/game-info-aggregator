package io.github.italodgsilva.presentation.response.error

import io.github.italodgsilva.presentation.response.shared.Response
import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class ErrorResponse (
    val message: String
): Response