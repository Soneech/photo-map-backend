package org.soneech.photomap.marks.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal

data class MarkRequest(
    @field:NotNull(message = "Поле latitude не может быть пустым")
    val latitude: BigDecimal,

    @field:NotNull(message = "Поле longitude не может быть пустым")
    val longitude: BigDecimal,

    @field:NotBlank(message = "Название не может быть пустым")
    @field:Size(min = 3, max = 100, message = "Название должно содержать от 3 до 100 символов")
    val name: String,

    val description: String?,

    val categoryId: String,
)
