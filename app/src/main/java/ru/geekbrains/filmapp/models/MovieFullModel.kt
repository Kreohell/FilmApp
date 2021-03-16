package ru.geekbrains.filmapp.models

import com.google.gson.annotations.SerializedName

data class MovieFullModel(
        @SerializedName("id") val id: Int,
        @SerializedName("genres") val genres: List<GenresModel>,
        @SerializedName("overview") val overview: String?,
        @SerializedName("poster_path") val poster_path: String,
        @SerializedName("release_date") val release_date: String,
        @SerializedName("runtime") val runtime: Int?,
        @SerializedName("title") val title: String,
        @SerializedName("video") val video: Boolean,
        @SerializedName("production_countries") val production_countries : List<ProductionCountries>,
        @SerializedName("vote_average") val vote_average : Double
)
