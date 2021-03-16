package ru.geekbrains.filmapp.response

import ru.geekbrains.filmapp.models.Movie
import java.io.Serializable

data class MoviesResponse(
    var page: Int,
    var results: List<Movie>
) : Serializable
