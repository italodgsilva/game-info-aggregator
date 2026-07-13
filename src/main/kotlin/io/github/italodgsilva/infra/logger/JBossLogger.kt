package io.github.italodgsilva.infra.logger

import org.jboss.logging.Logger
import io.github.italodgsilva.application.logger.Logger as ApplicationLogger

internal fun String.toMessageFormat(): String {
    var index = 0

    return replace(Regex("""\{\}""")) {
        "{${index++}}"
    }
}

class JBossLogger(
    private val logger: Logger,
) : ApplicationLogger {
    override fun info(
        message: String,
        vararg args: Any?,
    ) {
        logger.infov(message.toMessageFormat(), *args)
    }

    override fun warn(
        message: String,
        vararg args: Any?,
    ) {
        logger.warnv(message.toMessageFormat(), *args)
    }

    override fun error(
        message: String,
        throwable: Throwable?,
        vararg args: Any?,
    ) {
        logger.errorv(message.toMessageFormat(), throwable, *args)
    }
}
