package org.soneech.photomap.common.extension

import org.springframework.http.HttpStatus

class BadRequestException(
    override val message: String,
    val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST,
): RuntimeException(message)