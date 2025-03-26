package org.soneech.photomap.auth.dto.response.errors

data class BadRequestResponse(
    val code: String,
    val message: String,
)