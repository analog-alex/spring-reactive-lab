package io.analog.alex.reactive.controller

import io.analog.alex.reactive.errors.NonUniqueNameException
import io.analog.alex.reactive.model.Media
import io.analog.alex.reactive.service.CoroutineMediaService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/media")
class MediaController(
    val mediaService: CoroutineMediaService
) {

    @GetMapping
    suspend fun getAll(): Flow<Media> = mediaService.findAll()

    @GetMapping("/search")
    suspend fun search(@RequestParam(required = false) author: String?): Flow<Media> {
        return author?.let {
            mediaService.findByAuthor(it)
        } ?: emptyFlow()
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun create(@RequestBody entity: Media): Media {
        return mediaService.create(entity) ?: throw NonUniqueNameException()
    }
}