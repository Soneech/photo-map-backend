package org.soneech.photomap.data.jooq.service

import org.soneech.photomap.data.jooq.exception.CreationException
import org.soneech.photomap.data.jooq.exception.NotFoundException
import org.soneech.photomap.data.jooq.generated.tables.pojos.Users
import org.soneech.photomap.data.jooq.repository.UsersRepository
import org.springframework.stereotype.Service

@Service
class UsersDataService(
    private val usersRepository: UsersRepository,
) {

    fun getById(id: Long): Users {
        return usersRepository.getById(id)
            ?: throw NotFoundException("User with id = $id not found")
    }

    fun getByEmail(email: String): Users {
        return usersRepository.getByEmail(email)
            ?: throw NotFoundException("User with email = $email not found")
    }

    fun create(user: Users): Users {
        return usersRepository.create(user)
            ?: throw CreationException("Cannot create user with data: $user")
    }
}