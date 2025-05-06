package org.soneech.photomap.marks.dto.response

import java.math.BigDecimal
import java.time.LocalDateTime

data class MarkResponse(
    val id: Long,
    val authorId: Long,
    val authorName: String?,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    val name: String,
    val description: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
)
