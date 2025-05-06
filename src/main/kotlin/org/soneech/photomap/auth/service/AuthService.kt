package org.soneech.photomap.auth.service

import org.soneech.photomap.auth.dto.request.AuthRequest
import org.soneech.photomap.auth.dto.request.RegistrationRequest
import org.soneech.photomap.auth.dto.response.AuthUserResponse
import org.soneech.photomap.auth.dto.response.RegisteredUserResponse
import org.soneech.photomap.auth.exception.AlreadyExistsException
import org.soneech.photomap.auth.exception.AuthProcessException
import org.soneech.photomap.auth.jwt.JwtUtil
import org.soneech.photomap.auth.model.Roles
import org.soneech.photomap.data.jooq.generated.tables.pojos.Users
import org.soneech.photomap.profiles.service.UsersDataService
import org.springframework.http.HttpStatus
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
        val user = usersDataService.getByEmail(request.email)
        val token = UsernamePasswordAuthenticationToken(
            request.email,
            request.password
        )
        try {
            authenticationManager.authenticate(token)
        } catch (ex: Exception) {
            throw AuthProcessException(
                httpStatus = HttpStatus.UNAUTHORIZED,
                message = "Неверная почта или пароль"
            )
        }

        val jwt = jwtUtil.generateToken(request.email)
        return AuthUserResponse(requireNotNull(user.id), jwt)
    }

    fun register(request: RegistrationRequest): RegisteredUserResponse {
        if (usersDataService.existsByEmail(request.email)) {
            throw AlreadyExistsException("Пользователь с email ${request.email} уже существует")
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