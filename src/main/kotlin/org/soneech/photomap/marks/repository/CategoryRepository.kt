package org.soneech.photomap.marks.repository

import org.jooq.DSLContext
import org.soneech.photomap.data.jooq.generated.tables.pojos.Category
import org.soneech.photomap.data.jooq.generated.tables.references.CATEGORY
import org.springframework.stereotype.Repository

@Repository
class CategoryRepository(
    private val dsl: DSLContext,
) {

    fun getAll(): List<Category> {
        return dsl.selectFrom(CATEGORY)
            .fetchInto(Category::class.java)
    }

    fun getById(id: Long): Category? {
        return dsl.selectFrom(CATEGORY)
            .where(CATEGORY.ID.eq(id))
            .fetchOneInto(Category::class.java)
    }
}