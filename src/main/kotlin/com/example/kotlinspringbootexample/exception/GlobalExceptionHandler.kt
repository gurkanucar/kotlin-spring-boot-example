package com.example.kotlinspringbootexample.exception

import com.example.kotlinspringbootexample.model.CustomError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler
    fun userNotFoundException(ex: UserNotFoundException): ResponseEntity<CustomError> {
        val errorMessage = CustomError(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage)
    }

    @ExceptionHandler
    fun addressNotFoundException(ex: AddressNotFoundException): ResponseEntity<CustomError> {
        val errorMessage = CustomError(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage)
    }

}