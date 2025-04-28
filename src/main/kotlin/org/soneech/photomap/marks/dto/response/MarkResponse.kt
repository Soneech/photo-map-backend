package org.soneech.photomap.marks.dto.response

import java.math.BigDecimal
import java.time.LocalDateTime

data class MarkResponse(
    val id: Long,
    val userId: Long,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    val name: String,
    val description: String?,
    val likes: Long? = 0,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
)
