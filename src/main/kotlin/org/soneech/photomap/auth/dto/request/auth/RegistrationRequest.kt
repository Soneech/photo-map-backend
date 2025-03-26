package org.soneech.photomap.auth.dto.request.auth

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RegistrationRequest(
    @field:NotBlank(message = "Не может быть пустым")
    @field:Size(min = 3, max = 100, message = "Имя должно содержать не менее 3 и не более 100 символов")
    val name: String,

    @field:NotBlank(message = "Не может быть пустым")
    @field:Size(min = 6, max = 320, message = "Почта должна содержать не менее 6 и не более 320 символов")
    @field:Email(message = "Не является корректной электронной почтой")
    val email: String,

    @field:NotBlank(message = "Не может быть пустым")
    @field:Size(min = 8, max = 16, message = "Пароль должен содержать от 8 до 16 символов")
    val password: String,
)