package com.example.kotlinspringbootexample.service

import com.example.kotlinspringbootexample.exception.AddressNotFoundException
import com.example.kotlinspringbootexample.model.Address
import com.example.kotlinspringbootexample.repository.AddressRepository
import com.example.kotlinspringbootexample.request.CreateAddressRequest
import com.example.kotlinspringbootexample.request.UpdateAddressRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AddressService(
    @Autowired private val addressRepository: AddressRepository,
    @Autowired private val userService: UserService
) {

    fun getAllAddresses(): List<Address> = addressRepository.findAll()

    fun getAddressesByUser(id: Long): List<Address> = addressRepository.getAddressByUser(userService.getUserByID(id))

    fun getById(id: Long): Address {
        return addressRepository.findById(id)
            .orElseThrow { AddressNotFoundException("address not found!") }
    }

    fun createAddress(addressRequest: CreateAddressRequest): Address {
        return addressRepository.save(CreateAddressRequest.Mapper.toAddress(addressRequest))
    }

    fun updateAddress(addressRequest: UpdateAddressRequest): Address {
        val existing = getById(addressRequest.id)
        return addressRepository.save(UpdateAddressRequest.Mapper.toAddress(addressRequest, existing))
    }

    fun deleteAddress(id: Long) {
        val existing = getById(id)
        addressRepository.delete(existing)
    }
}
