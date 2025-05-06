package org.soneech.photomap.marks.controller

import org.soneech.photomap.auth.model.UserCredentials
import org.soneech.photomap.data.jooq.generated.tables.pojos.Comment
import org.soneech.photomap.marks.dto.request.CommentRequest
import org.soneech.photomap.marks.dto.response.CommentResponse
import org.soneech.photomap.marks.service.CommentService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/comments")
class CommentController(
    private val commentService: CommentService,
) {

    @PostMapping
    fun createComment(
        @RequestBody commentRequest: CommentRequest,
        @AuthenticationPrincipal userCredentials: UserCredentials
    ): ResponseEntity<CommentResponse> {
        val userId = requireNotNull(userCredentials.user.id)
        val userName = requireNotNull(userCredentials.user.name)
        val comment = commentRequest.toComment(userId)

        val created = commentService.create(comment)
        return ResponseEntity.ok(created.toResponse(userName))
    }

    @GetMapping("/{id}")
    fun getCommentsForMark(@PathVariable("id") markId: Long): ResponseEntity<List<CommentResponse>> {
        val comments = commentService.getAllForMark(markId)
        return ResponseEntity.ok(comments)
    }

    @DeleteMapping("/{id}")
    fun deleteComment(
        @PathVariable("id") commentId: Long,
        @AuthenticationPrincipal userCredentials: UserCredentials,
    ): ResponseEntity<Void> {
        val userId = requireNotNull(userCredentials.user.id)
        commentService.delete(commentId, userId)

        return ResponseEntity.ok().build()
    }

    fun CommentRequest.toComment(userId: Long) =
        Comment(
            userId = userId,
            markId = markId.toLong(),
            text = text,
            createdAt = LocalDateTime.now()
        )

    fun Comment.toResponse(userName: String) =
        CommentResponse(
            id = requireNotNull(id),
            userId = requireNotNull(userId),
            userName = userName,
            markId = requireNotNull(markId),
            text = requireNotNull(text),
            createdAt = requireNotNull(createdAt),
        )
}