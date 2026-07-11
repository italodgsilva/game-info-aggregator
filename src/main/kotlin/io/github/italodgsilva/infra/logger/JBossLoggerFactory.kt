package io.github.italodgsilva.infra.logger

import io.github.italodgsilva.application.logger.LoggerFactory
import jakarta.enterprise.context.ApplicationScoped
import org.jboss.logging.Logger

@ApplicationScoped
class JBossLoggerFactory : LoggerFactory {
    override fun getLogger(clazz: Class<*>): JBossLogger {
        val logger = Logger.getLogger(clazz)
        return JBossLogger(logger)
    }
}
