package com.example.kotlinspringbootexample.model

import javax.persistence.*

@Entity
@Table(name = "address")
data class Address(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,
    val title: String,
    val country: String,
    val city: String,
    val town: String? = null,
    val detail: String
)
