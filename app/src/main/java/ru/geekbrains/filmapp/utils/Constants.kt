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

        const val APP_PREFERENCE = "mySettings"
        const val APP_PREFERENCE_ADULT_CONTENT = "adultContent"

        const val ACTOR_PLACE_OF_BIRTH = "ActorPlaceOfBirth"
        const val ACTOR_NAME = "ActorName"
        const val ACTOR_PHOTO = "PhotoPath"
    }

}