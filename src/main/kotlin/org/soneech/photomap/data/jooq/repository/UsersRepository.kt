package org.soneech.photomap.data.jooq.repository

import org.jooq.DSLContext
import org.soneech.photomap.data.jooq.generated.tables.pojos.Users
import org.soneech.photomap.data.jooq.generated.tables.records.UsersRecord
import org.soneech.photomap.data.jooq.generated.tables.references.USERS
import org.springframework.stereotype.Repository

@Repository
class UsersRepository(
    private val dsl: DSLContext,
) {

    fun getById(id: Long): Users? {
        return dsl.selectFrom(USERS)
            .where(USERS.ID.eq(id))
            .fetchOneInto(Users::class.java)
    }

    fun getByEmail(email: String): Users? {
        return dsl.selectFrom(USERS)
            .where(USERS.EMAIL.eq(email))
            .fetchOneInto(Users::class.java)
    }

    fun create(user: Users): Users? {
        val record = UsersRecord(user)

        return dsl.insertInto(USERS)
            .set(record)
            .returning()
            .fetchOneInto(Users::class.java)

    }
}