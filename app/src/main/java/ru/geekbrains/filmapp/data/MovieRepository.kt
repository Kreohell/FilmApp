package ru.geekbrains.filmapp.data

import androidx.lifecycle.LiveData
import ru.geekbrains.filmapp.models.ActorFullInfoModel
import ru.geekbrains.filmapp.models.MovieFullModel
import ru.geekbrains.filmapp.utils.convertFullActorToFavorite
import ru.geekbrains.filmapp.utils.covertFullMovieToWant
import ru.geekbrains.filmapp.utils.covertFullMovieToWatched
import ru.geekbrains.filmapp.utils.getCountry

class MovieRepository(private val movieDao: MovieDao) {
    val readWatchedMovies: LiveData<List<WatchedMovie>> = movieDao.readWatchedMovies()

    val readWantMovies: LiveData<List<WantMovie>> = movieDao.readWantMovies()

    val readFavoriteActors: LiveData<List<FavoriteActor>> = movieDao.readFavoriteActors()

    suspend fun addMovieToWant(movieFullModel: MovieFullModel) =
        movieDao.addMovieToWant(covertFullMovieToWant(movieFull = movieFullModel))

    suspend fun addMovieToWatched(movieFullModel: MovieFullModel) =
        movieDao.addMovieToWatched(covertFullMovieToWatched(movieFull = movieFullModel))

    suspend fun isMovieInWant(id: Int) = movieDao.isMovieInWant(id = id)

    suspend fun isMovieInWatched(id: Int) = movieDao.isMovieInWatched(id = id)

    fun getCommentary(id: Int) = movieDao.getCommentary(movieId = id)

    suspend fun saveComment(id: Int, text: String) =
        movieDao.saveComment(Commentary(0, movieId = id, text = text))

    suspend fun saveActor(actorFullInfoModel: ActorFullInfoModel) =
        movieDao.saveActor(convertFullActorToFavorite(actorFullInfoModel = actorFullInfoModel))

    suspend fun removeActor(actorId: Int) = movieDao.removeActor(actorId = actorId)

    fun getFavorite(actorId: Int) = movieDao.getFavorite(actorId = actorId)
}