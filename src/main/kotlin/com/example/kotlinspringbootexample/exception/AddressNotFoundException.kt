package com.example.kotlinspringbootexample.exception

import java.lang.RuntimeException

class AddressNotFoundException(message: String) : RuntimeException(message) {
}