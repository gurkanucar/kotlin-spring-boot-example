package com.example.kotlinspringbootexample.request

import com.example.kotlinspringbootexample.model.Address
import com.example.kotlinspringbootexample.model.User
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UpdateAddressRequest(
    val id: Long,
    val title: String,
    val country: String,
    val city: String,
    val town: String? = null,
    val detail: String
) {
    object Mapper {
        fun toAddress(address: UpdateAddressRequest, existing: Address) =
            Address(
                id = existing.id,
                user = existing.user,
                title = address.title,
                country = address.country,
                city = address.city,
                town = address.town,
                detail = address.detail
            )
    }
}