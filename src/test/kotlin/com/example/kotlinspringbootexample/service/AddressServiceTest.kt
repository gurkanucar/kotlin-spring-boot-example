package com.example.kotlinspringbootexample.service

import com.example.kotlinspringbootexample.converter.toAddress
import com.example.kotlinspringbootexample.exception.AddressNotFoundException
import com.example.kotlinspringbootexample.exception.UserNotFoundException
import com.example.kotlinspringbootexample.model.Address
import com.example.kotlinspringbootexample.model.User
import com.example.kotlinspringbootexample.repository.AddressRepository
import com.example.kotlinspringbootexample.request.CreateAddressRequest
import com.example.kotlinspringbootexample.request.UpdateAddressRequest
import com.example.kotlinspringbootexample.request.UserRequest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyLong
import java.util.*

internal class AddressServiceTest {

    private val addressRepository: AddressRepository = mockk()
    private val userService: UserService = mockk()
    private val addressService = AddressService(addressRepository, userService)


    @Test
    fun `should return list of addresses`() {
        //given
        val user = User(id = 1L, username = "username", email = "mail", name = "name")
        val user2 = User(id = 2L, username = "username2", email = "mail2", name = "name2")
        val expected: List<Address> = listOf(
            Address(
                title = "address1", country = "TR", city = "Edirne",
                town = "town", detail = "details", user = user
            ),
            Address(
                title = "address2", country = "TR", city = "Istanbul",
                town = "town", detail = "details", user = user2
            ),
            Address(
                title = "address3", country = "TR", city = "Istanbul",
                town = "town", detail = "details", user = user
            ),
        )
        // when
        every { addressRepository.findAll() } returns expected
        val actual = addressService.getAllAddresses()
        // then
        verify(exactly = 1) { addressRepository.findAll() }
        assertThat(expected.size).isEqualTo(3)
        assertEquals(expected, actual)
    }

    @Test
    fun `should return list of addresses by given user if user exists`() {
        //given
        val user = User(id = 1L, username = "username", email = "mail", name = "name")
        val expected: List<Address> = listOf(
            Address(
                title = "address1", country = "TR", city = "Edirne",
                town = "town", detail = "details", user = user
            ),
            Address(
                title = "address2", country = "TR", city = "Istanbul",
                town = "town", detail = "details", user = user
            ),
        )
        // when
        every { addressRepository.getAddressByUser(user) } returns expected
        every { userService.getUserByID(anyLong()) } returns user
        val actual = addressService.getAddressesByUser(anyLong())
        // then
        verify(exactly = 0) { addressRepository.findAll() }
        verify(exactly = 1) { addressRepository.getAddressByUser(user) }
        assertThat(expected.size).isEqualTo(2)
        assertEquals(expected, actual)
    }

    @Test
    fun `should return address if exists by given id`() {
        val user = User(id = 1L, username = "username", email = "mail", name = "name")
        val expected = Address(
            id = 1L, title = "address1", country = "TR", city = "Edirne",
            town = "town", detail = "details", user = user
        )
        every { addressRepository.findById(anyLong()) } returns Optional.of(expected)
        val actual = addressService.getById(anyLong())
        assertEquals(expected, actual)
    }

    @Test
    fun `should throw exception if address doesn't exists by given id`() {
        // when
        every { addressRepository.findById(anyLong()) } returns Optional.empty()
        var exceptionThrown: Boolean = false
        try {
            addressService.getById(anyLong())
        } catch (exception: AddressNotFoundException) {
            exceptionThrown = true
        }
        // then
        assertTrue(exceptionThrown)
        verify(exactly = 1) { addressRepository.findById(anyLong()) }
    }

    @Test
    fun `should return address after create`() {
        val user = User(id = 1L, username = "username", email = "mail", name = "name")
        val userRequest = UserRequest(id = 1L)
        val createAddressRequest = CreateAddressRequest(
            title = "address1", country = "TR", city = "Edirne",
            town = "town", detail = "details", user = userRequest
        )
        val expected = Address(
            id = 1L, title = "address1", country = "TR", city = "Edirne",
            town = "town", detail = "details", user = user
        )
        every { user.id?.let { userService.getUserByID(it) } } returns user
        every { addressRepository.save(any()) } returns expected
        val actual = addressService.createAddress(createAddressRequest)

        verify(exactly = 1) { user.id?.let { userService.getUserByID(it) } }
        assertEquals(expected, actual)
    }

    @Test
    fun `should return current address after update`() {
        val user = User(id = 1L, username = "username", email = "mail", name = "name")
        val updateAddressRequest = UpdateAddressRequest(
            id = 1L, title = "UPDATED_address1", country = "UPDATED_TR", city = "UPDATED_Edirne",
            town = "UPDATED_town", detail = "UPDATED_details"
        )
        val existingAddress = Address(
            id = 1L, title = "address1", country = "TR", city = "Edirne",
            town = "town", detail = "details", user = user
        )
        val expected = Address(
            id = 1L, title = "UPDATED_address1", country = "UPDATED_TR", city = "UPDATED_Edirne",
            town = "UPDATED_town", detail = "UPDATED_details", user = user
        )
        every { addressRepository.findById(updateAddressRequest.id) } returns Optional.of(expected)
        every { addressRepository.save(updateAddressRequest.toAddress(existingAddress)) } returns expected
        val actual = addressService.updateAddress(updateAddressRequest)

        verify(exactly = 1) { addressRepository.findById(updateAddressRequest.id) }
        verify(exactly = 1) { addressRepository.save(updateAddressRequest.toAddress(existingAddress)) }
        assertEquals(expected, actual)
    }

    @Test
    fun `should delete address if exists by given id`() {
        val user = User(id = 1L, username = "username", email = "mail", name = "name")
        val existingAddress = Address(
            id = 1L, title = "address1", country = "TR", city = "Edirne",
            town = "town", detail = "details", user = user
        )
        every { addressRepository.findById(1L) } returns Optional.of(existingAddress)
        every { addressRepository.delete(existingAddress) } returns Unit
        addressService.deleteAddress(1L)
        verify(exactly = 1) { addressRepository.findById(1L) }
        verify(exactly = 1) { addressRepository.delete(existingAddress) }
    }
}