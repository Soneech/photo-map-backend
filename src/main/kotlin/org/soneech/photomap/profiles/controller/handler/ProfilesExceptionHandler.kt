package org.soneech.photomap.profiles.controller.handler

import org.soneech.photomap.data.jooq.exception.NotFoundException
import org.soneech.photomap.profiles.dto.response.errors.NotFoundResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ProfilesExceptionHandler {

    @ExceptionHandler
    fun handleNotFoundException(exception: NotFoundException): ResponseEntity<NotFoundResponse> {
        val response = exception.toNotFoundResponse()
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(response)
    }

    fun NotFoundException.toNotFoundResponse() = NotFoundResponse(
        message = message
    )
}