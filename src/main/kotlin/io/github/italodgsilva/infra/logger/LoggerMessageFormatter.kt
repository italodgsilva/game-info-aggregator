package io.github.italodgsilva.infra.logger

internal object LoggerMessageFormatter {
    fun format(message: String): String {
        var index = 0

        return message.replace(Regex("""\{\}""")) {
            "{${index++}}"
        }
    }
}
