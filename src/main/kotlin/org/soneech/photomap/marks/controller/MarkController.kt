package org.soneech.photomap.marks.controller

import jakarta.validation.Valid
import org.soneech.photomap.auth.model.UserCredentials
import org.soneech.photomap.auth.util.extension.getFieldsErrors
import org.soneech.photomap.common.exception.BadRequestException
import org.soneech.photomap.data.jooq.generated.tables.pojos.Mark
import org.soneech.photomap.marks.dto.request.MarkRequest
import org.soneech.photomap.marks.dto.response.MarkResponse
import org.soneech.photomap.marks.service.MarkDataService
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.util.LinkedMultiValueMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/marks")
class MarkController(
    private val markDataService: MarkDataService,
) {

    @GetMapping("/main")
    fun getMarksMainInfo(): ResponseEntity<List<MarkResponse>> {
        val response = markDataService.getAll().map { mark -> mark.toMarkResponse() }
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}",  produces = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun getMarkById(@PathVariable("id") id: Long): ResponseEntity<LinkedMultiValueMap<String, HttpEntity<*>>> {
        val markData = markDataService.getFullDataById(id)
        val body = LinkedMultiValueMap<String, HttpEntity<*>>()

        val jsonHeaders = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            setContentDispositionFormData("metadata", null)
        }
        val authorName = markData.author.name
        val category = markData.category
        val entity = HttpEntity(markData.mark.toMarkResponse(authorName, category.name), jsonHeaders)
        body.add("metadata", entity)

        markData.videos.forEach { fileContainer ->
            val bytes = requireNotNull(fileContainer.bytes)
            val resource = ByteArrayResource(bytes)
            val headers = HttpHeaders().apply {
                contentType = MediaType.parseMediaType("video/mp4")
                setContentDispositionFormData("videos", fileContainer.key)
            }
            body.add("videos", HttpEntity(resource, headers))
        }

        markData.photos.forEach { fileContainer ->
            val bytes = requireNotNull(fileContainer.bytes)
            val resource = ByteArrayResource(bytes)
            val headers = HttpHeaders().apply {
                contentType = MediaType.IMAGE_JPEG
                setContentDispositionFormData("photos", fileContainer.key)
            }
            body.add("photos", HttpEntity(resource, headers))
        }

        return ResponseEntity.ok().body(body)
    }

    @PostMapping("/create", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createMark(
        @Valid @RequestPart("mark") markRequest: MarkRequest,
        bindingResult: BindingResult,
        @RequestPart("photos", required = false) photos: List<MultipartFile>?,
        @RequestPart("videos", required = false) videos: List<MultipartFile>?,
        @AuthenticationPrincipal userCredentials: UserCredentials
    ): ResponseEntity<MarkResponse> {
        if (bindingResult.hasErrors()) {
            val fieldsAndErrors = bindingResult.getFieldsErrors()
            throw BadRequestException("Некорректные данные для создания метки", fieldsAndErrors)
        }

        val userId = userCredentials.user.id
        var mark = markRequest.toMark(requireNotNull(userId))
        mark = markDataService.create(mark, photos, videos)

        val response = mark.toMarkResponse(userCredentials.user.name)
        return ResponseEntity.ok(response)
    }

    fun MarkRequest.toMark(userId: Long) = Mark(
        userId = userId,
        latitude = latitude,
        longitude = longitude,
        name = name,
        description = description,
        categoryId = categoryId.toLong()
    )

    fun Mark.toMarkResponse(
        authorName: String? = null,
        categoryName: String? = null,
    ) = MarkResponse(
        id = requireNotNull(id),
        authorId = requireNotNull(userId),
        authorName = authorName,
        latitude = requireNotNull(latitude),
        longitude = requireNotNull(longitude),
        categoryId = categoryId,
        categoryName = categoryName,
        name = requireNotNull(name),
        description = requireNotNull(description),
        createdAt = requireNotNull(createdAt),
    )
}