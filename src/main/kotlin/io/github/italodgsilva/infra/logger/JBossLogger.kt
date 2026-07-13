package io.github.italodgsilva.infra.logger

import org.jboss.logging.Logger
import io.github.italodgsilva.application.logger.Logger as ApplicationLogger

class JBossLogger(
    private val logger: Logger,
) : ApplicationLogger {
    override fun info(
        message: String,
        vararg args: Any?,
    ) {
        logger.infov(LoggerMessageFormatter.format(message), *args)
    }

    override fun warn(
        message: String,
        vararg args: Any?,
    ) {
        logger.warnv(LoggerMessageFormatter.format(message), *args)
    }

    override fun error(
        message: String,
        throwable: Throwable?,
        vararg args: Any?,
    ) {
        logger.errorv(LoggerMessageFormatter.format(message), throwable, *args)
    }
}
