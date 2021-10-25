# Spring Reactive Lab
A small experiment with Spring Boot's reactive stack with Kotlin coroutine's flavour.

## Instructions

Before launching, run `docker-compose up -d mongo redis`

Then you can use the `localhost:8080/media` endpoint. After a GET try a POST with a body like: 

```JSON
    {
        "id": "5",
        "type": "MOVIE",
        "name": "Minority Report",
        "author": "Spielberg",
        "createdAt": "2021-10-25",
        "rating": 5
    }
```