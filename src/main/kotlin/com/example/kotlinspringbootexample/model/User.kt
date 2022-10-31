package com.example.kotlinspringbootexample.model

data class User(
    val id: Long,
    val username: String,
    val age: Int? = null
) {
    constructor(
        id: Long,
        username: String
    ) : this(id, username, null)
}
