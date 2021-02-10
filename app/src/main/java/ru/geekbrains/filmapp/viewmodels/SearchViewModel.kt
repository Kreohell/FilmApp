package ru.geekbrains.filmapp.viewmodels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.filmapp.repositories.SearchRepository
import ru.geekbrains.filmapp.response.MoviesResponse

class SearchViewModel : ViewModel(), LifecycleObserver {

    private var searchRepository = SearchRepository()

    private var observingMovies = MutableLiveData<MoviesResponse>()

    fun getMovies() = observingMovies

    fun textChanged(query: String) {
        if (query == "") {
            return
        }
        searchRepository.searchMovies()
    }

}