package org.soneech.photomap.auth.controller

import org.soneech.photomap.data.jooq.generated.tables.pojos.Roles
import org.soneech.photomap.data.jooq.service.RolesDataService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController(
    private val rolesDataService: RolesDataService,
) {
    @GetMapping("/{id}")
    fun getRoleById(@PathVariable("id") id: Long): Roles {
        return rolesDataService.getById(id)
    }
}