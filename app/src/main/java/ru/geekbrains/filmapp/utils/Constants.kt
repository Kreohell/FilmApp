package ru.geekbrains.filmapp.utils

import android.annotation.SuppressLint
import java.util.*

class Constants {

    companion object {
        @SuppressLint("ConstantLocale")
        val locale: String = Locale.getDefault().language

        const val BASE_URL = "https://api.themoviedb.org/"
        const val POSTERS_BASE_URL = "https://image.tmdb.org/t/p/w500"
        const val POSTERS_BASE_URL_SMALL = "https://image.tmdb.org/t/p/w400"
    }

}