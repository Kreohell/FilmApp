package ru.geekbrains.filmapp.viewmodels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.filmapp.interactors.StringInteractor
import ru.geekbrains.filmapp.models.MovieDetails
import ru.geekbrains.filmapp.models.ProductionCountries
import ru.geekbrains.filmapp.repositories.MovieDetailsRepository

class MovieDetailsViewModel(private val stringInteractor: StringInteractor) : ViewModel(),
    LifecycleObserver {
    private val observingMovie = MutableLiveData<MovieDetails>()

    private val movieDetailsRepository = MovieDetailsRepository()

    fun getObservedMovie() = observingMovie

    fun getReceivedMovieInfo(movieId: Int) {
        movieDetailsRepository.getMovieInfo(observingMovie = observingMovie, id = movieId)
    }

    fun convertDate(date: String) = date.substring(0, 4)

    fun getCountry(productionCountries: List<ProductionCountries>): String {
        if (productionCountries.isEmpty()) return stringInteractor.textUnknown
        return productionCountries[0].name
    }

    fun getDescription(description: String?): String {
        if (description == null) return stringInteractor.textNoDescription
        return description
    }

    fun getDuration(runtime: Int?): String {
        if (runtime == null) return stringInteractor.textUnknown
        val hours = runtime / 60
        val minutes = runtime % 60
        return String.format("%dh %02dmin", hours, minutes)
    }
}