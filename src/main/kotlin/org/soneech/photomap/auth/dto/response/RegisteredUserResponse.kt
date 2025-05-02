package org.soneech.photomap.auth.dto.response

data class RegisteredUserResponse(
    val id: Long,
    val name: String,
    val email: String,
)