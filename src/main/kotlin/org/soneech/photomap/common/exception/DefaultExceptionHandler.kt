package org.soneech.photomap.common.exception

import org.soneech.photomap.common.exception.dto.response.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class DefaultExceptionHandler {

    @ExceptionHandler
    fun handleBadRequestException(exception: BadRequestException): ResponseEntity<ErrorResponse> {
        val response = exception.toErrorResponse()
        return ResponseEntity
            .status(exception.httpStatus)
                .body(response)
    }

    fun BadRequestException.toErrorResponse() = ErrorResponse(
        code = httpStatus.value().toString(),
        message = message,
        errors = errors
    )
}