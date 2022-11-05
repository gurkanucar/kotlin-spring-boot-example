package com.example.kotlinspringbootexample.model

import com.example.kotlinspringbootexample.dto.UserDTO
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
) {
    companion object {
        fun User.toDTO(): UserDTO {
            return UserDTO(
                id = this.id,
                username = "DTO: " + this.username,
                email = this.email,
                name = this.name,
                surname = this.surname,
            )
        }
    }
}
