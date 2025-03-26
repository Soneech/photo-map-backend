package org.soneech.photomap.auth.jwt

import com.auth0.jwt.exceptions.JWTVerificationException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.soneech.photomap.auth.service.UserCredentialsService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class JwtFilter(
    private val jwtUtil: JwtUtil,
    private val userCredentialsService: UserCredentialsService
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (!authHeader.isNullOrBlank() && authHeader.startsWith(BEARER_PREFIX)) {
            val jwt = authHeader.substring(JWT_BEGIN_INDEX)

            if (jwt.isBlank()) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
                return
            } else {
                try {
                    val userClaim = jwtUtil.validateTokenAndRetrieveClaim(jwt)
                    val userDetails = userCredentialsService.loadUserByUsername(userClaim)

                    val authToken = UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.password,
                        userDetails.authorities
                    )

                    if (SecurityContextHolder.getContext().authentication == null) {
                        SecurityContextHolder.getContext().authentication = authToken
                    }
                } catch (ex: JWTVerificationException) {
                    response.sendError(HttpStatus.UNAUTHORIZED.value())
                    return
                }
            }
        }

        filterChain.doFilter(request, response)
    }

    companion object {
        private const val BEARER_PREFIX = "Bearer "
        private const val JWT_BEGIN_INDEX = 7
    }
}
