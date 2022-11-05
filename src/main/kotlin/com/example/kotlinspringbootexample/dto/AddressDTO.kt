package com.example.kotlinspringbootexample.dto

data class AddressDTO(
    val id: Long? = null,
    val title: String,
    val country: String,
    val city: String,
    val town: String? = null,
    val detail: String
)