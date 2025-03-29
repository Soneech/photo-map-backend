package org.soneech.photomap.auth.dto.response.errors

data class ErrorResponse(
    val code: String,
    val message: String,
    val errors: Set<String> = emptySet(),
)