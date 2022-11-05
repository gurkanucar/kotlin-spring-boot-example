package com.example.kotlinspringbootexample.service

import com.example.kotlinspringbootexample.exception.UserNotFoundException
import com.example.kotlinspringbootexample.model.User
import com.example.kotlinspringbootexample.repository.UserRepository
import com.example.kotlinspringbootexample.request.CreateUserRequest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyLong
import java.util.*

internal class UserServiceTest {

    private val userRepository: UserRepository = mockk()
    private val userService = UserService(userRepository)

    @Test
    fun `should return list of users`() {
        //given
        val expected: List<User> = listOf(
            User(id = 1L, username = "username", email = "mail", name = "name"),
            User(id = 2L, username = "username2", email = "mail2", name = "name2")
        )
        // when
        every { userRepository.findAll() } returns expected
        val actual = userService.getAllUsers()
        // then
        verify(exactly = 1) { userRepository.findAll() }
        assertThat(expected.size).isEqualTo(2)
        assertEquals(expected, actual)
    }

    @Test
    fun `should return user after create`() {
        //given
        val createUserRequest = CreateUserRequest(username = "username", email = "mail", name = "name")
        val expected = User(id = 1L, username = "username", email = "mail", name = "name")
        //when
        every { userRepository.save(any()) } returns expected
        val actual = userService.createUser(createUserRequest)
        // then
        verify(exactly = 1) { userRepository.save(any()) }
        assertEquals(expected, actual)
    }

    @Test
    fun `should return user if exists by given id`() {
        //given
        val expected = User(id = 1L, username = "username", email = "mail", name = "name")

        every { userRepository.findById(1L) } returns Optional.of(expected)
        val actual = userService.getUserByID(1L)
        // then
        verify(exactly = 1) { userRepository.findById(1L) }
        assertEquals(expected, actual)
    }

    @Test
    fun `should throw exception if user doesn't exist by given id`() {
        // when
        every { userRepository.findById(anyLong()) } returns Optional.empty()
        var exceptionThrown: Boolean = false
        try {
            userService.getUserByID(anyLong())
        } catch (exception: UserNotFoundException) {
            exceptionThrown = true
        }
        // then
        assertTrue(exceptionThrown)
        verify(exactly = 1) { userRepository.findById(anyLong()) }
    }

}