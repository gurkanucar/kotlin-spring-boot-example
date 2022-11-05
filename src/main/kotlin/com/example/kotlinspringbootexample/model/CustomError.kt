package com.example.kotlinspringbootexample.model

import org.springframework.http.HttpStatus

class CustomError(
    val status: Int? = null,
    var message: String? = null
) {
    constructor(httpStatus: HttpStatus, message: String) : this(
        status = httpStatus.value(),
        message = message
    )
}