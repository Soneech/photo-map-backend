package org.soneech.photomap.auth.service

import org.soneech.photomap.auth.model.UserCredentials
import org.soneech.photomap.data.jooq.service.UsersDataService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserCredentialsService(
    private val usersDataService: UsersDataService
) : UserDetailsService {
    override fun loadUserByUsername(email: String?): UserDetails {
        val user = usersDataService.getByEmail(requireNotNull(email))
        return UserCredentials(user)
    }
}