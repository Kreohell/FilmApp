package ru.geekbrains.filmapp.response

import com.google.gson.annotations.SerializedName
import ru.geekbrains.filmapp.models.Actor

data class CastResponse(
    @SerializedName("cast") val cast: List<Actor>
)
