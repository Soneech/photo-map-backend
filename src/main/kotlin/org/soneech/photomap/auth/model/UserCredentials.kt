package org.soneech.photomap.auth.model

import org.soneech.photomap.data.jooq.generated.tables.pojos.Users
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserCredentials(val user: Users) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val role = Roles.valueOf(requireNotNull(user.role))
        return mutableListOf(role)
    }

    override fun getPassword(): String {
        return requireNotNull(user.password)
    }

    override fun getUsername(): String {
        return requireNotNull(user.email)
    }
}