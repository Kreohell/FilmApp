package ru.geekbrains.filmapp.response

import ru.geekbrains.filmapp.models.Movie

data class MoviesResponse(
    var page: Int,
    var results: List<Movie>
)
