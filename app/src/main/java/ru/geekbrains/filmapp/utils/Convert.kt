package ru.geekbrains.filmapp.utils

import ru.geekbrains.filmapp.data.FavoriteActor
import ru.geekbrains.filmapp.data.WantMovie
import ru.geekbrains.filmapp.data.WatchedMovie
import ru.geekbrains.filmapp.models.ActorFullInfoModel
import ru.geekbrains.filmapp.models.MovieFullModel
import ru.geekbrains.filmapp.models.SimpleMovie

val movies = arrayListOf<SimpleMovie>()

fun convertWantToMovie(list: List<WantMovie>): List<SimpleMovie> {
    movies.clear()
    list.forEach {
        movies.add(
            SimpleMovie(
                movieId = it.id,
                posterPath = it.posterPath,
                movieTitle = it.movieTitle,
                movieReleaseDate = it.movieReleaseDate,
                movieReleaseCountry = it.movieReleaseCountry,
                movieRuntime = it.movieRuntime,
                movieRating = it.movieRating
            )
        )
    }
    return movies
}

fun convertWatchedToMovie(list: List<WatchedMovie>): List<SimpleMovie> {
    movies.clear()
    list.forEach {
        movies.add(
            SimpleMovie(
                movieId = it.id,
                posterPath = it.posterPath,
                movieTitle = it.movieTitle,
                movieReleaseDate = it.movieReleaseDate,
                movieReleaseCountry = it.movieReleaseCountry,
                movieRuntime = it.movieRuntime,
                movieRating = it.movieRating
            )
        )
    }
    return movies
}

fun covertFullMovieToWatched(movieFull: MovieFullModel) =
    WatchedMovie(
        0,
        movieId = movieFull.id,
        posterPath = movieFull.poster_path,
        movieTitle = movieFull.title,
        movieRuntime = movieFull.runtime,
        movieReleaseCountry = getCountry(movieFull.production_countries),
        movieReleaseDate = movieFull.release_date,
        movieRating = movieFull.vote_average
    )

fun covertFullMovieToWant(movieFull: MovieFullModel) =
    WantMovie(
        0,
        movieId = movieFull.id,
        posterPath = movieFull.poster_path,
        movieTitle = movieFull.title,
        movieRuntime = movieFull.runtime,
        movieReleaseCountry = getCountry(movieFull.production_countries),
        movieReleaseDate = movieFull.release_date,
        movieRating = movieFull.vote_average
    )

fun convertFullActorToFavorite(actorFullInfoModel: ActorFullInfoModel) =
    FavoriteActor(
        0,
        actorId = actorFullInfoModel.id,
        actorName = actorFullInfoModel.name,
        actorPosterPath = actorFullInfoModel.photo,
        placeOfBirth = actorFullInfoModel.placeOfBirth
    )