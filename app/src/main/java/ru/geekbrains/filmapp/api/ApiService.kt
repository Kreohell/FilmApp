package ru.geekbrains.filmapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.geekbrains.filmapp.models.MovieDetails
import ru.geekbrains.filmapp.response.MoviesResponse

interface ApiService {

    @GET(value = "/3/movie/popular")
    fun getPopular(@Query("api_key") key: String): Call<MoviesResponse>

    @GET(value = "/3/movie/now_playing")
    fun getNowPlaying(@Query("api_key") key: String): Call<MoviesResponse>

    @GET(value = "/3/movie/upcoming")
    fun getUpcoming(@Query("api_key") key: String): Call<MoviesResponse>

    @GET(value = "/3/movie/top_rated")
    fun getTop(@Query("api_key") key: String): Call<MoviesResponse>

    @GET(value = "/3/search/movie")
    fun searchMovie(
        @Query("api_key") key: String,
        @Query("query") query: String
    ): Call<MoviesResponse>

    @GET(value = "/3/movie/{id}")
    fun getDetails(
        @Path("id") id: Int,
        @Query("api_key") query: String
    ): Call<MovieDetails>

}