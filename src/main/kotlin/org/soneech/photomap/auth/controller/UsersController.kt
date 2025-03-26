package org.soneech.photomap.auth.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController {

    @GetMapping
    fun getAllUsers(): String { // test security
        return "users"
    }
}