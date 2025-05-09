package org.soneech.photomap.marks.service

import org.soneech.photomap.data.jooq.generated.tables.pojos.Category
import org.soneech.photomap.marks.repository.CategoryRepository
import org.springframework.stereotype.Service
import org.soneech.photomap.data.jooq.exception.NotFoundException

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {

    fun getAll(): List<Category> {
        return categoryRepository.getAll()
    }

    fun getById(id: Long): Category {
        return categoryRepository.getById(id)
            ?: throw NotFoundException("Категория с id = $id не найдена")
    }
}