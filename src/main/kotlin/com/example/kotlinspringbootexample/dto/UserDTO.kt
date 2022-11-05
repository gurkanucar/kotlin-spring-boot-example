package com.example.kotlinspringbootexample.dto

data class UserDTO(
    val id: Long? = null,
    val username: String,
    val email: String,
    val name: String,
    val surname: String? = null,
)