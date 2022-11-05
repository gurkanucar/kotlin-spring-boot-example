package com.example.kotlinspringbootexample.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "`user`")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val username: String,
    val email: String,
    val name: String,
    val surname: String? = null,
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    val items: List<Address> = mutableListOf()
)