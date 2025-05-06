package org.soneech.photomap.marks.controller

import org.soneech.photomap.auth.model.UserCredentials
import org.soneech.photomap.data.jooq.generated.tables.pojos.Likes
import org.soneech.photomap.marks.dto.LikesDto
import org.soneech.photomap.marks.service.LikesService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/likes")
class LikesController(
    private val likesService: LikesService,
) {

    @GetMapping("/{id}")
    fun getLikesForMark(@PathVariable("id") markId: Long): ResponseEntity<List<LikesDto>> {
        val likes = likesService.getLikesForMark(markId)
        return ResponseEntity.ok(likes.toResponse())
    }

    @PostMapping("/{id}")
    fun handleLikeForMark(
        @PathVariable("id") markId: Long,
        @AuthenticationPrincipal userCredentials: UserCredentials
    ): ResponseEntity<LikesDto> {
        val userId = requireNotNull(userCredentials.user.id)
        likesService.handleLike(
            markId = markId,
            userId = userId
        )
        val response = LikesDto(markId, userId)
        return ResponseEntity.ok(response)
    }

    fun List<Likes>.toResponse() = this.map { like ->
        LikesDto(
            markId = requireNotNull(like.markId),
            userId = requireNotNull(like.userId)
        )
    }
}