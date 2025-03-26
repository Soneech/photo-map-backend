package org.soneech.photomap.auth.controller

import jakarta.validation.Valid
import org.soneech.photomap.auth.dto.request.auth.AuthRequest
import org.soneech.photomap.auth.dto.request.auth.RegistrationRequest
import org.soneech.photomap.auth.dto.response.auth.AuthUserResponse
import org.soneech.photomap.auth.dto.response.auth.RegisteredUserResponse
import org.soneech.photomap.auth.exception.AuthException
import org.soneech.photomap.auth.exception.RegistrationException
import org.soneech.photomap.auth.service.AuthService
import org.soneech.photomap.auth.util.extension.getFieldsErrors
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping("/registration")
    fun registration(@Valid @RequestBody request: RegistrationRequest, bindingResult: BindingResult):
            ResponseEntity<RegisteredUserResponse> {
        if (bindingResult.hasErrors()) {
            val fieldsAndErrors = bindingResult.getFieldsErrors()
            throw RegistrationException(HttpStatus.BAD_REQUEST, fieldsAndErrors)
        }

        val registeredUserResponse = authService.register(request)
        return ResponseEntity.ok(registeredUserResponse)
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody request: AuthRequest, bindingResult: BindingResult):
            ResponseEntity<AuthUserResponse> {
        if (bindingResult.hasErrors()) {
            val fieldsAndErrors = bindingResult.getFieldsErrors()
            throw AuthException(HttpStatus.BAD_REQUEST, fieldsAndErrors)
        }

        val authUserResponse = authService.authenticate(request)
        return ResponseEntity.ok(authUserResponse)
    }
}