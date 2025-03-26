package org.soneech.photomap.auth.controller.handler

import org.soneech.photomap.auth.dto.response.errors.BadParamsResponse
import org.soneech.photomap.auth.dto.response.errors.BadRequestResponse
import org.soneech.photomap.auth.exception.AuthProcessException
import org.soneech.photomap.auth.exception.ExistenceException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class AuthExceptionHandler {

    @ExceptionHandler
    fun handleAuthProcessException(exception: AuthProcessException): ResponseEntity<BadParamsResponse> {
        val response = exception.toBadParamsResponse()
        return ResponseEntity
            .status(exception.httpStatus)
            .body(response)
    }

    @ExceptionHandler
    fun handleExistenceException(exception: ExistenceException): ResponseEntity<BadRequestResponse> {
        val response = exception.toBadRequestResponse()
        return ResponseEntity
            .badRequest()
            .body(response)
    }

    fun AuthProcessException.toBadParamsResponse() = BadParamsResponse(
        code = httpStatus.value().toString(),
        message = message,
        fieldsErrors = fieldsErrors,
    )

    fun ExistenceException.toBadRequestResponse() = BadRequestResponse(
        code = HttpStatus.BAD_REQUEST.value().toString(),
        message = message,
    )
}