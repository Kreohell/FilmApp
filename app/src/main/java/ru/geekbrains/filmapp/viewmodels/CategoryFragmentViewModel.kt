package ru.geekbrains.filmapp.viewmodels

import androidx.lifecycle.*
import ru.geekbrains.filmapp.repositories.CategoryRepository
import ru.geekbrains.filmapp.response.MoviesResponse

class CategoryFragmentViewModel : ViewModel(), LifecycleObserver {

    private var currentPosition = 0
    private var categoryRepository = CategoryRepository()
    private var observingTabPosition = MutableLiveData<Int>()
    private var observingMovies = MutableLiveData<MoviesResponse>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun init() = changeTab(currentPosition)

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun orientationChanged() {
        observingTabPosition.value = currentPosition
    }

    fun getObservedMovies() = observingMovies
    fun getPosition() = observingTabPosition

    fun changeTab(position: Int) {
        if (position == currentPosition && observingMovies.value != null) return
        when (position) {
            0 -> categoryRepository.getPopularMovies()
            1 -> categoryRepository.getNowPlayingMovies()
            2 -> categoryRepository.getUpcomingMovies()
            3 -> categoryRepository.getTopMovies()
        }
        currentPosition = position
    }

}