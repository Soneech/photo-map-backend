package org.soneech.photomap.auth.dto.request.auth

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class AuthRequest(
    @field:NotBlank(message = "Почта не может быть пустая")
    @field:Size(min = 6, max = 320, message = "Почта должна содержать не менее 6 и не более 320 символов")
    @field:Email(message = "Не является корректной электронной почтой")
    val email: String,

    @field:NotBlank(message = "Пароль не может быть пустым")
    @field:Size(min = 8, max = 16, message = "Пароль должен содержать от 8 до 16 символов")
    val password: String,
)