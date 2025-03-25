package org.soneech.photomap.data.jooq.service

import org.soneech.photomap.data.jooq.exception.NotFoundException
import org.soneech.photomap.data.jooq.generated.tables.pojos.Roles
import org.soneech.photomap.data.jooq.repository.RolesRepository
import org.springframework.stereotype.Service

@Service
class RolesDataService(
    private val rolesRepository: RolesRepository,
) {
    fun getById(id: Long): Roles {
        return rolesRepository.getById(id)
                ?: throw NotFoundException("Role with id = $id not found")
    }
}