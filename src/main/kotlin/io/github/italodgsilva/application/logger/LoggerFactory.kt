package io.github.italodgsilva.application.logger

interface LoggerFactory {
    fun getLogger(clazz: Class<*>): Logger
}
