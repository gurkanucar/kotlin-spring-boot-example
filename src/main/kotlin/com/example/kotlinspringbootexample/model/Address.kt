package com.example.kotlinspringbootexample.model

import com.example.kotlinspringbootexample.dto.AddressDTO
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "address")
data class Address(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,
    val title: String,
    val country: String,
    val city: String,
    val town: String? = null,
    val detail: String
) {
    companion object {
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
    }
}