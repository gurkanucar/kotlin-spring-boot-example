package com.example.kotlinspringbootexample.request

import com.example.kotlinspringbootexample.model.Address
import com.example.kotlinspringbootexample.model.User
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateAddressRequest(
    @NotBlank @NotNull
    val user: User,
    val title: String,
    val country: String,
    val city: String,
    val town: String? = null,
    val detail: String
) {
    object Mapper {
        fun toAddress(address: CreateAddressRequest) =
            Address(
                user = address.user,
                title = address.title,
                country = address.country,
                city = address.city,
                town = address.town,
                detail = address.detail
            )
    }
}