package ru.geekbrains.filmapp.viewmodels

import android.app.Application
import androidx.lifecycle.*
import ru.geekbrains.filmapp.data.*
import ru.geekbrains.filmapp.models.SimpleMovie

class HomeFragmentViewModel(application: Application) : AndroidViewModel(application),
        LifecycleObserver {

    fun deleteMovie(item: SimpleMovie) {}

    var readWantMovies: LiveData<List<WantMovie>>
    var readWatchMovies: LiveData<List<WatchedMovie>>
    var readFavoriteActors: LiveData<List<FavoriteActor>>
    private val repository: MovieRepository

    init {
        val movieDao = MoviesDatabase.getDatabase(application).movieDao()
        repository = MovieRepository(movieDao = movieDao)
        readWantMovies = repository.readWantMovies
        readWatchMovies = repository.readWatchedMovies
        readFavoriteActors = repository.readFavoriteActors
    }
}