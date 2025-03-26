package org.soneech.photomap.profiles.dto.response.profiles

open class UserDataResponse()

data class UserPrivateDataResponse( // TODO добавить созданные метки/категории
    val id: Long,
    val name: String,
    val email: String,
    val role: String,
): UserDataResponse()

data class UserPublicDataResponse( // TODO добавить созданные метки/категории
    val name: String,
): UserDataResponse()