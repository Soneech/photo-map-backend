package org.soneech.photomap.auth.model

import org.springframework.security.core.GrantedAuthority

enum class Roles : GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    override fun getAuthority(): String {
        return name
    }
}