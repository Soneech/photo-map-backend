package org.soneech.photomap.auth.dto.response.auth

data class RegisteredUserResponse(
    val id: Long,
    val name: String,
    val email: String,
)