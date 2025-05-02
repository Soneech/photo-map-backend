package org.soneech.photomap.common.exception.dto.response

data class ErrorResponse(
    val code: String,
    val message: String,
    val errors: Set<String> = emptySet(),
)