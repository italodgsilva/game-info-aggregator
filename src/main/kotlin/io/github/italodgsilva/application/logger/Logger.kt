package io.github.italodgsilva.application.logger

interface Logger {
    fun info(
        message: String,
        vararg args: Any?,
    )

    fun warn(
        message: String,
        vararg args: Any?,
    )

    fun error(
        message: String,
        throwable: Throwable? = null,
        vararg args: Any?,
    )
}
