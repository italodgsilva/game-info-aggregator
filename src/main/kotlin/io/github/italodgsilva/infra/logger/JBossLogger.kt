package io.github.italodgsilva.infra.logger

import jakarta.enterprise.context.ApplicationScoped
import org.jboss.logging.Logger
import io.github.italodgsilva.application.logger.Logger as ApplicationLogger

@ApplicationScoped
class JBossLogger : ApplicationLogger {
    private val logger = Logger.getLogger(JBossLogger::class.java)

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

    private fun String.toMessageFormat(): String {
        var index = 0

        return replace(Regex("""\{\}""")) {
            "{${index++}}"
        }
    }
}
