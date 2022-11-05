package com.example.kotlinspringbootexample.request

import com.example.kotlinspringbootexample.model.User

data class CreateUserRequest(
    val username: String,
    val email: String,
    val name: String,
    val surname: String? = null,
) {
    object Mapper {
        fun toUser(user: CreateUserRequest) =
            User(
                username = user.username,
                email = user.email,
                name = user.name,
                surname = user.surname
            )
    }
}
