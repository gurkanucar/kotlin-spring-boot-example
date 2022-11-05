package com.example.kotlinspringbootexample.request

import javax.validation.constraints.NotNull

data class CreateAddressRequest(
    @NotNull
    val user: UserRequest,
    val title: String,
    val country: String,
    val city: String,
    val town: String? = null,
    val detail: String
)