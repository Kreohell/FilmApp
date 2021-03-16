package ru.geekbrains.filmapp.viewmodels

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.geekbrains.filmapp.data.MovieRepository
import ru.geekbrains.filmapp.data.MoviesDatabase
import ru.geekbrains.filmapp.interactors.StringInteractor
import ru.geekbrains.filmapp.models.ActorFullInfoModel
import ru.geekbrains.filmapp.models.MovieFullModel
import ru.geekbrains.filmapp.models.ProductionCountries
import ru.geekbrains.filmapp.repositories.FullMovieRepository
import ru.geekbrains.filmapp.response.CastResponse

class MovieDetailsViewModel(application: Application, private val stringInteractor: StringInteractor) :
    AndroidViewModel(application),
    LifecycleObserver {

    private val _observingMovie = MutableLiveData<MovieFullModel>()
    private val _observingCast = MutableLiveData<CastResponse>()
    private val _observingActor = MutableLiveData<ActorFullInfoModel>()

    private val fullMovieRepository: FullMovieRepository
    private val movieRepository: MovieRepository

    init {
        val movieDao = MoviesDatabase.getDatabase(application).movieDao()
        movieRepository = MovieRepository(movieDao = movieDao)
        fullMovieRepository = FullMovieRepository()
    }

    fun getObservedMovie() = _observingMovie
    fun getCast() = _observingCast
    fun getObservingActor() = _observingActor

    fun getReceivedMovieInfo(movieId: Int) = fullMovieRepository.apply {
        getMovieInfo(_observingMovie = _observingMovie, id = movieId)
        getCast(_observingActors = _observingCast, id = movieId)
    }

    fun getComment(movieId: Int) = movieRepository.getCommentary(id = movieId)

    fun getFavorite(actorId: Int) = movieRepository.getFavorite(actorId = actorId)

    fun addMovieToWant(movieFullModel: MovieFullModel) = viewModelScope.launch {
        movieRepository.addMovieToWant(movieFullModel = movieFullModel)
    }

    fun addMovieToWatched(movieFullModel: MovieFullModel) = viewModelScope.launch {
        movieRepository.addMovieToWatched(movieFullModel = movieFullModel)
    }

    fun saveComment(id: Int, text: String) = viewModelScope.launch {
        movieRepository.saveComment(id = id, text = text)
    }

    fun addActorToFavorite(actorFullInfoModel: ActorFullInfoModel) = viewModelScope.launch {
        movieRepository.saveActor(actorFullInfoModel = actorFullInfoModel)
    }

    fun removeActorFromFavorite(actorId: Int) = viewModelScope.launch {
        movieRepository.removeActor(actorId = actorId)
    }

    fun checkWantBtn(id: Int) = runBlocking {
        movieRepository.isMovieInWant(id = id)
    }

    fun checkWatchedBtn(id: Int) = runBlocking {
        movieRepository.isMovieInWatched(id = id)
    }

    fun getDescription(description: String?): String {
        description?.let { return description } ?: return stringInteractor.textNoDescription
    }

    fun getActorInfo(actorId: Int) =
        fullMovieRepository.getActorInfo(actorId = actorId, observer = _observingActor)

    fun getCountry(countries: List<ProductionCountries>): String {
        if (countries.count() == 0) return stringInteractor.textUnknown
        return countries.first().name
    }
}