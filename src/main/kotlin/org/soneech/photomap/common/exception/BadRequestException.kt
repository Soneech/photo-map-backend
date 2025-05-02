package org.soneech.photomap.common.exception

import org.springframework.http.HttpStatus

class BadRequestException(
    override val message: String,
    val errors: Set<String>,
    val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST,
): RuntimeException(message)