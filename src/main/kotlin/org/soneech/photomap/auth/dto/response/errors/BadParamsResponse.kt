package org.soneech.photomap.auth.dto.response.errors

import com.fasterxml.jackson.annotation.JsonProperty

data class BadParamsResponse(
    val code: String,
    val message: String,

    @JsonProperty("fields_errors")
    val fieldsErrors: Map<String, Set<String>>,
)