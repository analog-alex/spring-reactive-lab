package io.analog.alex.reactive.repository

import io.analog.alex.reactive.model.Media
import io.analog.alex.reactive.model.Type
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface ReactiveMediaRepository : ReactiveMongoRepository<Media, String> {
    fun findAllByType(type: Type): Flux<Media>
    fun findAllByAuthor(author: String): Flux<Media>
}