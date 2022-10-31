package com.example.kotlinspringbootexample.controller

import com.example.kotlinspringbootexample.model.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController {

    @GetMapping
    fun sayHello(@RequestParam("name", required = false, defaultValue = "defaultName") name: String?): String {
        return "Hello $name!"
    }


    @GetMapping("/users")
    fun sayHello(): List<User> {
        return mutableListOf(
            User(1, "gurkan", 21),
            User(2, "ahmet"),
            User(3, "mehmet"),
            User(4, "sezai", 50),
        )
    }
}