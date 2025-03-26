package org.soneech.photomap.data.jooq.exception

import java.lang.RuntimeException

class NotFoundException(override val message: String) : RuntimeException()

class CreationException(override val message: String) : RuntimeException()
