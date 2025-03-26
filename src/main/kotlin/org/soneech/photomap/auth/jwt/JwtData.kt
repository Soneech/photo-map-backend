package org.soneech.photomap.auth.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("app.jwt")
class JwtData {
    lateinit var secret: String
    lateinit var subject: String
    lateinit var issuer: String
    var ttl: Long = 3600
}