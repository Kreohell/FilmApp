package ru.geekbrains.filmapp.response

import com.google.gson.annotations.SerializedName
import ru.geekbrains.filmapp.models.Actor
import java.io.Serializable

data class ActorsResponse(

    @SerializedName("page") val page : Int,
    @SerializedName("results") val results : List<Actor>,
    @SerializedName("total_results") val total_results : Int,
    @SerializedName("total_pages") val total_pages : Int

) : Serializable

