package com.example.kotlinspringbootexample.exception

import java.lang.RuntimeException
import javax.validation.constraints.NotNull

class UserNotFoundException(@NotNull message: String) : RuntimeException(message) {
}