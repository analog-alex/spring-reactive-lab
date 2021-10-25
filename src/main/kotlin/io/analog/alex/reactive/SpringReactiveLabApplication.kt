package io.analog.alex.reactive

import io.analog.alex.reactive.model.Media
import io.analog.alex.reactive.model.Type
import io.analog.alex.reactive.repository.ReactiveMediaRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import org.springframework.stereotype.Component
import java.time.LocalDate

@SpringBootApplication
@EnableReactiveMongoRepositories
class SpringReactiveLabApplication

fun main(args: Array<String>) {
	runApplication<SpringReactiveLabApplication>(*args)
}

@Component
class DataInjector(val reactiveMediaRepository: ReactiveMediaRepository) : CommandLineRunner {

	override fun run(vararg args: String?) {
		reactiveMediaRepository.deleteAll().subscribe()

		val media1 = Media(
			id = "1",
			type =  Type.PAITING,
			name = "Black Prince at Crecy",
			author = "Some Guy",
			createdAt = LocalDate.now(),
			rating = 5
		)
		val media2 = Media(
			id = "2",
			type =  Type.MOVIE,
			name = "Jurassic Park",
			author = "Stephen Spielberg",
			createdAt = LocalDate.now(),
			rating = 5
		)
		val media3 = Media(
			id = "3",
			type =  Type.ALBUM,
			name = "Dark Side of the Moon",
			author = "Pink Floyd",
			createdAt = LocalDate.now(),
			rating = 5
		)

		reactiveMediaRepository.insert(media1).subscribe()
		reactiveMediaRepository.insert(media2).subscribe()
		reactiveMediaRepository.insert(media3).subscribe()
	}
}