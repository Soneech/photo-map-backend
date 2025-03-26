package org.soneech.photomap.auth.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {  // test security
    @GetMapping("/test")
    fun test(): String {
        return "test"
    }

    @GetMapping("/test/authorized")
    fun testAuthorized(): String {
        return "test authorized access"
    }
}