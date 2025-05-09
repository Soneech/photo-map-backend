package org.soneech.photomap.marks.controller

import org.soneech.photomap.data.jooq.generated.tables.pojos.Category
import org.soneech.photomap.marks.service.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/categories")
class CategoryController(
    private val categoryService: CategoryService,
) {

    @GetMapping
    fun getAllCategories(): ResponseEntity<List<Category>> {
        val response = categoryService.getAll()
        return ResponseEntity.ok(response)
    }
}