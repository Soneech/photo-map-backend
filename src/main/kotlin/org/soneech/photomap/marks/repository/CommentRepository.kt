package org.soneech.photomap.marks.repository

import org.jooq.DSLContext
import org.soneech.photomap.data.jooq.generated.tables.pojos.Comment
import org.soneech.photomap.data.jooq.generated.tables.records.CommentRecord
import org.soneech.photomap.data.jooq.generated.tables.references.COMMENT
import org.springframework.stereotype.Repository

@Repository
class CommentRepository(
    private val dsl: DSLContext
) {

    fun create(comment: Comment): Comment? {
        val record = CommentRecord(comment)
        return dsl.insertInto(COMMENT)
            .set(record)
            .returning()
            .fetchOneInto(Comment::class.java)
    }

    fun getAllForMark(markId: Long): List<Comment> {
        return dsl.selectFrom(COMMENT)
            .where(COMMENT.MARK_ID.eq(markId))
            .fetchInto(Comment::class.java)
    }

    fun delete(commentId: Long, userId: Long) {
        dsl.delete(COMMENT)
            .where(
                COMMENT.ID.eq(commentId),
                COMMENT.USER_ID.eq(userId)
            )
            .execute()
    }
}