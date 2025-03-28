package org.soneech.photomap.auth.service

import org.soneech.photomap.auth.dto.request.auth.AuthRequest
import org.soneech.photomap.auth.dto.request.auth.RegistrationRequest
import org.soneech.photomap.auth.dto.response.auth.AuthUserResponse
import org.soneech.photomap.auth.dto.response.auth.RegisteredUserResponse
import org.soneech.photomap.auth.exception.ExistenceException
import org.soneech.photomap.auth.jwt.JwtUtil
import org.soneech.photomap.auth.model.Roles
import org.soneech.photomap.data.jooq.generated.tables.pojos.Users
import org.soneech.photomap.data.jooq.service.UsersDataService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val usersDataService: UsersDataService,
    private val jwtUtil: JwtUtil,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder,
) {

    fun authenticate(request: AuthRequest): AuthUserResponse {
        if (!usersDataService.existsByEmail(request.email)) {
            throw ExistenceException("Пользователь с email ${request.email} не найден")
        }

        val token = UsernamePasswordAuthenticationToken(
            request.email,
            request.password
        )
        authenticationManager.authenticate(token)

        val jwt = jwtUtil.generateToken(request.email)
        return AuthUserResponse(jwt)
    }

    fun register(request: RegistrationRequest): RegisteredUserResponse {
        if (usersDataService.existsByEmail(request.email)) {
            throw ExistenceException("Пользователь с email ${request.email} уже существует")
        }
        val user = request.toUser()
        val registeredUser = usersDataService.create(user)
        return registeredUser.toRegisteredResponse()
    }

    fun RegistrationRequest.toUser() = Users(
        name = name,
        email = email,
        password = passwordEncoder.encode(password),
        role = Roles.ROLE_USER.name,
    )

    fun Users.toRegisteredResponse() = RegisteredUserResponse(
        id = requireNotNull(id),
        name = requireNotNull(name),
        email = requireNotNull(email),
    )
}