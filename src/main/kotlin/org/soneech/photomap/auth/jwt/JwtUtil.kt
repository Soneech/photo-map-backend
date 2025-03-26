package org.soneech.photomap.auth.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import mu.KLogging
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

@Component
class JwtUtil(private val jwtData: JwtData) {

    fun generateToken(email: String): String {
        val currentDateTime = OffsetDateTime.now()
        val expirationDate = currentDateTime.plusSeconds(jwtData.ttl)
        return JWT.create()
            .withSubject(jwtData.subject)
            .withClaim(EMAIL_CLAIM, email)
            .withIssuedAt(currentDateTime.toInstant())
            .withIssuer(jwtData.issuer)
            .withExpiresAt(expirationDate.toInstant())
            .sign(Algorithm.HMAC256(jwtData.secret))
    }

    fun validateTokenAndRetrieveClaim(token: String): String {
        val verifier: JWTVerifier = JWT.require(Algorithm.HMAC256(jwtData.secret))
            .withSubject(jwtData.subject)
            .withIssuer(jwtData.issuer)
            .build()
        return try {
            val jwt: DecodedJWT = verifier.verify(token)
            jwt.getClaim(EMAIL_CLAIM).asString()
        } catch (ex: JWTVerificationException) {
            logger.error("Invalid jwt: $token")
            logger.error(ex.message)
            throw ex
        }
    }

    companion object: KLogging() {
        private const val EMAIL_CLAIM = "email"
    }
}
