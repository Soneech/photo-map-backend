package org.soneech.photomap.marks.repository

import org.jooq.DSLContext
import org.soneech.photomap.data.jooq.generated.tables.pojos.Mark
import org.soneech.photomap.data.jooq.generated.tables.records.MarkRecord
import org.soneech.photomap.data.jooq.generated.tables.references.MARK
import org.springframework.stereotype.Repository

@Repository
class MarkRepository(
    private val dsl: DSLContext,
) {

    fun create(mark: Mark): Mark? {
        val record = MarkRecord(mark)
        return dsl.insertInto(MARK)
            .set(record)
            .returning()
            .fetchOneInto(Mark::class.java)
    }

    fun getAll(): List<Mark> {
        return dsl.selectFrom(MARK)
            .fetchInto(Mark::class.java)
    }
}