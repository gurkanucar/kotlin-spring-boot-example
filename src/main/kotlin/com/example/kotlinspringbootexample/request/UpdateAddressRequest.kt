package com.example.kotlinspringbootexample.request

data class UpdateAddressRequest(
    val id: Long,
    val title: String,
    val country: String,
    val city: String,
    val town: String? = null,
    val detail: String
)