package ru.geekbrains.filmapp.repositories

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Response
import ru.geekbrains.filmapp.api.ApiService
import ru.geekbrains.filmapp.models.MovieDetails
import ru.geekbrains.filmapp.net.RetrofitInstance

class MovieDetailsRepository {
    private var apiService: ApiService = RetrofitInstance.api

    fun getMovieInfo(observingMovie: MutableLiveData<MovieDetails>, id: Int) {
        apiService.getDetails(id = id, query = "1fb92cebaf8f7372063847bb4ffe16e9").enqueue(object :
            retrofit2.Callback<MovieDetails> {
            override fun onResponse(
                call: Call<MovieDetails>,
                response: Response<MovieDetails>
            ) {
                observingMovie.value = response.body()
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) = t.printStackTrace()
        })
    }
}