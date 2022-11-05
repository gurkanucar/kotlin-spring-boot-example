package com.example.kotlinspringbootexample.request

import com.example.kotlinspringbootexample.model.Address
import com.example.kotlinspringbootexample.model.User
import javax.validation.constraints.NotNull

data class CreateAddressRequest(
    @NotNull
    val user: UserRequest,
    val title: String,
    val country: String,
    val city: String,
    val town: String? = null,
    val detail: String
) {
    object Mapper {
        fun toAddress(address: CreateAddressRequest, user: User) =
            Address(
                user = user,
                title = address.title,
                country = address.country,
                city = address.city,
                town = address.town,
                detail = address.detail
            )
    }
}