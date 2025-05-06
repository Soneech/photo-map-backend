package org.soneech.photomap.profiles.service

import org.soneech.photomap.data.jooq.exception.CreationException
import org.soneech.photomap.data.jooq.exception.NotFoundException
import org.soneech.photomap.data.jooq.generated.tables.pojos.Users
import org.soneech.photomap.profiles.repository.UsersRepository
import org.springframework.stereotype.Service

@Service
class UsersDataService(
    private val usersRepository: UsersRepository,
) {

    fun getById(id: Long): Users {
        return usersRepository.getById(id)
            ?: throw NotFoundException("Пользователь с id = $id не найден")
    }

    fun getByEmail(email: String): Users {
        return usersRepository.getByEmail(email)
            ?: throw NotFoundException("Пользователь с email = $email не найден")
    }

    fun getAll(): Set<Users> {
        return usersRepository.getAll()
    }

    fun existsByEmail(email: String): Boolean {
        return usersRepository.getByEmail(email) != null
    }

    fun create(user: Users): Users {
        return usersRepository.create(user)
            ?: throw CreationException("Ошибка создания пользователя с данными: $user")
    }
}