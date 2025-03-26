package org.soneech.photomap.auth.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

class ExistenceException(
    override val message: String
): RuntimeException()

open class AuthProcessException(
    open val httpStatus: HttpStatus,
    open val fieldsErrors: Map<String, Set<String>>,
    override val message: String,
): RuntimeException()

class AuthException(
    override val httpStatus: HttpStatus,
    override val fieldsErrors: Map<String, Set<String>>,
    override val message: String = "Некорректные данные аутентификации",
): AuthProcessException(httpStatus, fieldsErrors, message)

class RegistrationException(
    override val httpStatus: HttpStatus,
    override val fieldsErrors: Map<String, Set<String>>,
    override val message: String = "Некорректные данные регистрации",
): AuthProcessException(httpStatus, fieldsErrors, message)

