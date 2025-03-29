package org.soneech.photomap.auth.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

class AlreadyExistsException(
    override val message: String
): RuntimeException()

open class AuthProcessException(
    open val httpStatus: HttpStatus,
    open val errors: Set<String> = emptySet(),
    override val message: String,
): RuntimeException()

class AuthException(
    override val httpStatus: HttpStatus,
    override val errors: Set<String>,
    override val message: String = "Некорректные данные аутентификации",
): AuthProcessException(httpStatus, errors, message)

class RegistrationException(
    override val httpStatus: HttpStatus,
    override val errors: Set<String>,
    override val message: String = "Некорректные данные регистрации",
): AuthProcessException(httpStatus, errors, message)

