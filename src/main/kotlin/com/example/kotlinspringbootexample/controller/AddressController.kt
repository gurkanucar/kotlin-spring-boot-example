package com.example.kotlinspringbootexample.controller

import com.example.kotlinspringbootexample.converter.toDTO
import com.example.kotlinspringbootexample.dto.AddressDTO
import com.example.kotlinspringbootexample.request.CreateAddressRequest
import com.example.kotlinspringbootexample.request.UpdateAddressRequest
import com.example.kotlinspringbootexample.service.AddressService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/address")
class AddressController(@Autowired private val addressService: AddressService) {

    @GetMapping
    fun getAllAddresses(): ResponseEntity<List<AddressDTO>> =
        ResponseEntity.ok(addressService.getAllAddresses().map { it.toDTO() })

    @GetMapping("/{id}")
    fun getAllAddresses(@PathVariable id: Long): ResponseEntity<AddressDTO> =
        ResponseEntity.ok(addressService.getById(id).toDTO())

    @GetMapping("/by-user/{id}")
    fun getByUser(@PathVariable id: Long): ResponseEntity<List<AddressDTO>> =
        ResponseEntity.ok(addressService.getAddressesByUser(id).map { it.toDTO() })

    @PostMapping
    fun createAddress(@RequestBody createAddressRequest: CreateAddressRequest): ResponseEntity<AddressDTO> =
        ResponseEntity.status(HttpStatus.CREATED).body(addressService.createAddress(createAddressRequest).toDTO())

    @PutMapping
    fun updateAddress(@RequestBody updateAddressRequest: UpdateAddressRequest): ResponseEntity<AddressDTO> =
        ResponseEntity.ok(addressService.updateAddress(updateAddressRequest).toDTO())

    @DeleteMapping("/{id}")
    fun updateAddress(@PathVariable id: Long): ResponseEntity<Void> {
        addressService.deleteAddress(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}