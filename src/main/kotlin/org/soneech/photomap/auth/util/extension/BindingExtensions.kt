package org.soneech.photomap.auth.util.extension

import org.springframework.validation.BindingResult

fun BindingResult.getFieldsErrors(): Map<String, Set<String>> {
    val fieldsAndErrors = mutableMapOf<String, MutableSet<String>>()

    fieldErrors.forEach { fieldError ->
        val field = fieldError.field
        val message = fieldError.defaultMessage ?: "Неизвестная ошибка"

        fieldsAndErrors.computeIfAbsent(field) { mutableSetOf() }.add(message)
    }

    return fieldsAndErrors
}