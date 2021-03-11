package ru.geekbrains.filmapp.viewmodels

import android.app.Application
import androidx.lifecycle.*
import ru.geekbrains.filmapp.data.MovieRepository
import ru.geekbrains.filmapp.data.MoviesDatabase
import ru.geekbrains.filmapp.data.WantMovie
import ru.geekbrains.filmapp.data.WatchedMovie
import ru.geekbrains.filmapp.models.SimpleMovie

class HomeFragmentViewModel(application: Application) : AndroidViewModel(application),
        LifecycleObserver {

    fun deleteMovie(item: SimpleMovie) {}

    var readWantMovies: LiveData<List<WantMovie>>
    var readWatchMovies: LiveData<List<WatchedMovie>>
    private val repository: MovieRepository

    init {
        val movieDao = MoviesDatabase.getDatabase(application).movieDao()
        repository = MovieRepository(movieDao = movieDao)
        readWantMovies = repository.readWantMovies
        readWatchMovies = repository.readWatchedMovies
    }
}