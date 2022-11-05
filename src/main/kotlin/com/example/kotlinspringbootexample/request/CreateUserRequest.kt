package com.example.kotlinspringbootexample.request

data class CreateUserRequest(
    val username: String,
    val email: String,
    val name: String,
    val surname: String? = null,
)
