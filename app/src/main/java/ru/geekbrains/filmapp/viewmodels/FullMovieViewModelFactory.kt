package ru.geekbrains.filmapp.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.filmapp.interactors.StringInteractor
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class FullMovieViewModelFactory(
    private val stringInteractor: StringInteractor,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            return MovieDetailsViewModel(
                stringInteractor = stringInteractor,
                application = application) as T
        }
        throw IllegalArgumentException("ViewModel not found")
    }

}