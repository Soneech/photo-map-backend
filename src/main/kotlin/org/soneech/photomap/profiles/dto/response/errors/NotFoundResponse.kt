package org.soneech.photomap.profiles.dto.response.errors

import org.springframework.http.HttpStatus

data class NotFoundResponse(
    val code: String = HttpStatus.NOT_FOUND.value().toString(),
    val message: String,
)