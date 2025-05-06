package org.soneech.photomap.marks.repository

import org.jooq.DSLContext
import org.soneech.photomap.data.jooq.generated.tables.pojos.Likes
import org.soneech.photomap.data.jooq.generated.tables.records.LikesRecord
import org.soneech.photomap.data.jooq.generated.tables.references.LIKES
import org.springframework.stereotype.Repository

@Repository
class LikesRepository(
    private val dsl: DSLContext
) {

    fun getLikesForMark(markId: Long): List<Likes> {
        return dsl
            .selectFrom(LIKES)
            .where(LIKES.MARK_ID.eq(markId))
            .fetchInto(Likes::class.java)
    }

    fun addLike(likes: Likes): Likes? {
        val record = LikesRecord(likes)
        return dsl
            .insertInto(LIKES)
            .set(record)
            .returning()
            .fetchOneInto(Likes::class.java)
    }

    fun isExists(markId: Long, userId: Long): Boolean {
        return dsl.fetchExists(
            dsl.selectFrom(LIKES)
                .where(
                    LIKES.MARK_ID.eq(markId),
                    LIKES.USER_ID.eq(userId)
                )
        )
    }

    fun deleteLike(markId: Long, userId: Long) {
        dsl
            .delete(LIKES)
            .where(
                LIKES.MARK_ID.eq(markId),
                LIKES.USER_ID.eq(userId)
            )
            .execute()
    }

}