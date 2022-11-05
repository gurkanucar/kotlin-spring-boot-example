package com.example.kotlinspringbootexample.converter

import com.example.kotlinspringbootexample.dto.AddressDTO
import com.example.kotlinspringbootexample.model.Address
import com.example.kotlinspringbootexample.model.User
import com.example.kotlinspringbootexample.request.CreateAddressRequest
import com.example.kotlinspringbootexample.request.CreateUserRequest
import com.example.kotlinspringbootexample.request.UpdateAddressRequest

fun Address.toDTO(): AddressDTO {
    return AddressDTO(
        id = this.id,
        title = this.title,
        country = this.country,
        city = this.city,
        town = this.town,
        detail = this.detail
    )
}

fun CreateAddressRequest.toAddress(user: User): Address {
    return Address(
        user = user,
        title = this.title,
        country = this.country,
        city = this.city,
        town = this.town,
        detail = this.detail
    )
}

fun UpdateAddressRequest.toAddress(existing: Address): Address {
    return Address(
        id = existing.id,
        user = existing.user,
        title = this.title,
        country = this.country,
        city = this.city,
        town = this.town,
        detail = this.detail
    )
}
