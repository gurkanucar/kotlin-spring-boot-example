package com.example.kotlinspringbootexample.controller

import com.example.kotlinspringbootexample.converter.toDTO
import com.example.kotlinspringbootexample.dto.UserDTO
import com.example.kotlinspringbootexample.request.CreateUserRequest
import com.example.kotlinspringbootexample.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getUsers(): ResponseEntity<List<UserDTO>> =
        ResponseEntity.ok(userService.getAllUsers().map { it.toDTO() })

    @GetMapping(("/{id}"))
    fun getUserByID(@PathVariable id: Long): ResponseEntity<UserDTO> =
        ResponseEntity.ok(userService.getUserByID(id).toDTO())

    @PostMapping
    fun getUserByID(@RequestBody createUserRequest: CreateUserRequest): ResponseEntity<UserDTO> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(userService.createUser(createUserRequest).toDTO())
    }

}

