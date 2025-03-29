package org.soneech.photomap.auth.util.extension

import org.springframework.validation.BindingResult

fun BindingResult.getFieldsErrors(): Set<String> {
    val fieldsErrors = mutableSetOf<String>()

    fieldErrors.forEach { fieldError ->
        val message = fieldError.defaultMessage ?: "Неизвестная ошибка"
        fieldsErrors.add(message)
    }

    return fieldsErrors
}