package com.example.kotlinspringbootexample.controller

import com.example.kotlinspringbootexample.dto.UserDTO
import com.example.kotlinspringbootexample.model.User.Companion.toDTO
import com.example.kotlinspringbootexample.request.CreateUserRequest
import com.example.kotlinspringbootexample.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(@Autowired private val userService: UserService) {

    @GetMapping
    fun getUsers(): ResponseEntity<List<UserDTO>> =
        ResponseEntity.ok().body(userService.getAllUsers().map { it.toDTO() })

    @GetMapping(("/{id}"))
    fun getUserByID(@PathVariable id: Long): ResponseEntity<UserDTO> =
        ResponseEntity.ok().body(userService.getUserByID(id).toDTO())

    @PostMapping
    fun getUserByID(@RequestBody createUserRequest: CreateUserRequest): ResponseEntity<UserDTO> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(userService.createUser(createUserRequest).toDTO())
    }

}

