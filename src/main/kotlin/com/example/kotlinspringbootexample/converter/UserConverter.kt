package com.example.kotlinspringbootexample.converter

import com.example.kotlinspringbootexample.dto.UserDTO
import com.example.kotlinspringbootexample.model.User
import com.example.kotlinspringbootexample.request.CreateUserRequest

fun User.toDTO(): UserDTO {
    return UserDTO(
        id = this.id,
        username = this.username,
        email = this.email,
        name = this.name,
        surname = this.surname,
    )
}


fun CreateUserRequest.toUser(): User {
    return User(
        username = this.username,
        email = this.email,
        name = this.name,
        surname = this.surname
    )
}
