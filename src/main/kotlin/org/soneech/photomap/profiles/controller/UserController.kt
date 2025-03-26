package org.soneech.photomap.profiles.controller

import org.soneech.photomap.auth.model.Roles
import org.soneech.photomap.auth.model.UserCredentials
import org.soneech.photomap.data.jooq.generated.tables.pojos.Users
import org.soneech.photomap.data.jooq.service.UsersDataService
import org.soneech.photomap.profiles.dto.response.profiles.UserDataResponse
import org.soneech.photomap.profiles.dto.response.profiles.UserPrivateDataResponse
import org.soneech.photomap.profiles.dto.response.profiles.UserPublicDataResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController(
    private val usersDataService: UsersDataService,
) {

    @GetMapping
    fun getAll(): ResponseEntity<Set<UserPrivateDataResponse>> {
        val responseSet = usersDataService.getAll().toPrivateDataResponseSet()
        return ResponseEntity.ok(responseSet)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Long, @AuthenticationPrincipal authUser: UserCredentials):
            ResponseEntity<UserDataResponse> {
        val foundUser = usersDataService.getById(id)
        val response = if (authUser.isCurrentOrAdmin(foundUser.email)) {
            foundUser.toPrivateDataResponse()
        } else {
            foundUser.toPublicDataResponse()
        }

        return ResponseEntity.ok(response)
    }

    fun UserCredentials.isCurrentOrAdmin(foundUserEmail: String?) =
        username == foundUserEmail || authorities.contains(Roles.ROLE_ADMIN)

    fun Users.toPublicDataResponse() = UserPublicDataResponse(
        name = requireNotNull(name)
    )

    fun Users.toPrivateDataResponse() = UserPrivateDataResponse(
        id = requireNotNull(id),
        name = requireNotNull(name),
        email = requireNotNull(email),
        role = requireNotNull(role),
    )

    fun Set<Users>.toPrivateDataResponseSet() =
        this.map { it.toPrivateDataResponse() }.toSet()
}