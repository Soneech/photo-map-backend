package org.soneech.photomap.marks.model

import org.springframework.web.multipart.MultipartFile

data class FileContainer(
    val key: String,
    val bucket: String,
    val multipartFile: MultipartFile? = null,
    val bytes: ByteArray? = null,
)
