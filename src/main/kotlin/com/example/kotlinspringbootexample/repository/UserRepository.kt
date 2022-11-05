package com.example.kotlinspringbootexample.repository

import com.example.kotlinspringbootexample.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

}