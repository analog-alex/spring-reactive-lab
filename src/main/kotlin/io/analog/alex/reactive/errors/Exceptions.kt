package io.analog.alex.reactive.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class NonUniqueNameException : RuntimeException("Name already exists! Cannot duplicate name.")