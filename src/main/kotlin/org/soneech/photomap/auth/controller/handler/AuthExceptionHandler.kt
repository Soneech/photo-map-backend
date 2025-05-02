package org.soneech.photomap.auth.controller.handler

import org.soneech.photomap.common.exception.dto.response.ErrorResponse
import org.soneech.photomap.auth.exception.AuthProcessException
import org.soneech.photomap.auth.exception.AlreadyExistsException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class AuthExceptionHandler {

    @ExceptionHandler
    fun handleAuthProcessException(exception: AuthProcessException): ResponseEntity<ErrorResponse> {
        val response = exception.toErrorResponse()
        return ResponseEntity
            .status(exception.httpStatus)
            .body(response)
    }

    @ExceptionHandler
    fun handleExistenceException(exception: AlreadyExistsException): ResponseEntity<ErrorResponse> {
        val status = HttpStatus.CONFLICT
        val response = exception.toErrorResponse(status)
        return ResponseEntity
            .status(status)
            .body(response)
    }

    fun AuthProcessException.toErrorResponse() = ErrorResponse(
        code = httpStatus.value().toString(),
        message = message,
        errors = errors
    )

    fun AlreadyExistsException.toErrorResponse(status: HttpStatus) = ErrorResponse(
        code = status.value().toString(),
        message = message,
    )
}