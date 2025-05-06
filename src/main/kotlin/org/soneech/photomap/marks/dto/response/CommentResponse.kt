package org.soneech.photomap.marks.dto.response

import java.time.LocalDateTime

data class CommentResponse(
    val id: Long,
    val userId: Long,
    val userName: String,
    val markId: Long,
    val text: String,
    val createdAt: LocalDateTime,
)
