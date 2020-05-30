package io.analog.alex.reactive.controller

import io.analog.alex.reactive.model.Media
import io.analog.alex.reactive.repository.ReactiveMediaRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/media")
class MediaController(
    val reactiveMediaRepository: ReactiveMediaRepository
) {

    @GetMapping
    fun getAll(): Flux<Media> = reactiveMediaRepository.findAll()

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody entity: Media) = reactiveMediaRepository.save(entity)
}