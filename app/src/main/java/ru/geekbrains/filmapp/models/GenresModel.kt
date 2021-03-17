package ru.geekbrains.filmapp.models

import com.google.gson.annotations.SerializedName

data class GenresModel(
        @SerializedName("name") val name : String
)
