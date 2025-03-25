package org.soneech.photomap.data.jooq.repository

import org.jooq.DSLContext
import org.soneech.photomap.data.jooq.generated.tables.pojos.Roles
import org.soneech.photomap.data.jooq.generated.tables.references.ROLES
import org.springframework.stereotype.Repository

@Repository
class RolesRepository(
    private val dsl: DSLContext,
) {
    fun getById(id: Long): Roles? {
        return dsl.selectFrom(ROLES)
            .where(ROLES.ID.eq(id))
            .fetchOneInto(Roles::class.java)
    }
}