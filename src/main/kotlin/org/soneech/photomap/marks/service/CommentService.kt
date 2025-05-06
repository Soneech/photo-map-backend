package org.soneech.photomap.marks.service

import org.soneech.photomap.data.jooq.exception.CreationException
import org.soneech.photomap.data.jooq.generated.tables.pojos.Comment
import org.soneech.photomap.marks.dto.response.CommentResponse
import org.soneech.photomap.marks.repository.CommentRepository
import org.soneech.photomap.profiles.service.UsersDataService
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val usersDataService: UsersDataService,
) {

    fun create(comment: Comment): Comment {
        return commentRepository.create(comment)
            ?: throw CreationException(
                "Не удалось сохранить комментарий для пользователя с id = ${comment.userId}"
            )
    }

    fun getAllForMark(markId: Long): List<CommentResponse> {
        val comments = commentRepository.getAllForMark(markId)
        return comments.map { comment -> CommentResponse( // TODO выглядит очень плохо (спидран...)
            id = requireNotNull(comment.id),
            userId = requireNotNull(comment.userId),
            userName = requireNotNull(usersDataService.getById(comment.userId!!).name),
            markId = markId,
            text = requireNotNull(comment.text),
            createdAt = requireNotNull(comment.createdAt)
        ) }
    }

    fun delete(commentId: Long, userId: Long) {
        commentRepository.delete(commentId, userId)
    }
}