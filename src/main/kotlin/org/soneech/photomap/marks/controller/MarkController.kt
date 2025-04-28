package org.soneech.photomap.marks.controller

import jakarta.validation.Valid
import org.soneech.photomap.auth.model.UserCredentials
import org.soneech.photomap.auth.util.extension.getFieldsErrors
import org.soneech.photomap.common.extension.BadRequestException
import org.soneech.photomap.data.jooq.generated.tables.pojos.Mark
import org.soneech.photomap.marks.dto.request.MarkRequest
import org.soneech.photomap.marks.dto.response.MarkResponse
import org.soneech.photomap.marks.service.MarkDataService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/marks")
class MarkController(
    private val markDataService: MarkDataService,
) {

    @PostMapping("/create", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createMark(
        @Valid @RequestPart("mark") markRequest: MarkRequest,
        bindingResult: BindingResult,
        @RequestPart("photos", required = false) photos: List<MultipartFile>?,
        @RequestPart("videos", required = false) videos: List<MultipartFile>?,
        @AuthenticationPrincipal userCredentials: UserCredentials
    ): ResponseEntity<MarkResponse> {
        if (bindingResult.hasErrors()) {
            val fieldsAndErrors = bindingResult.getFieldsErrors() // TODO
            throw BadRequestException("Некорректные данные для создания метки")
        }

        val userId = userCredentials.user.id
        var mark = markRequest.toMark(requireNotNull(userId))
        mark = markDataService.create(mark, photos, videos)

        val response = mark.toCreateMarkResponse()
        return ResponseEntity.ok(response)
    }

    fun MarkRequest.toMark(userId: Long) = Mark(
        userId = userId,
        latitude = latitude,
        longitude = longitude,
        name = name,
        description = description,
    )

    fun Mark.toCreateMarkResponse() = MarkResponse(
        id = requireNotNull(id),
        userId = requireNotNull(userId),
        latitude = requireNotNull(latitude),
        longitude = requireNotNull(longitude),
        name = requireNotNull(name),
        description = requireNotNull(description),
        createdAt = requireNotNull(createdAt),
    )
}