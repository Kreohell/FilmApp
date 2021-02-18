package ru.geekbrains.filmapp.repositories

import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Response
import ru.geekbrains.filmapp.BuildConfig
import ru.geekbrains.filmapp.api.ApiService
import ru.geekbrains.filmapp.net.RetrofitInstance
import ru.geekbrains.filmapp.response.MoviesResponse


class CategoryRepository {

    private var apiService: ApiService = RetrofitInstance.api

    fun getPopularMovies(observingMovies: MutableLiveData<MoviesResponse>) {
        apiService.getPopular("1fb92cebaf8f7372063847bb4ffe16e9").enqueue(object :
            retrofit2.Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                @NonNull response: Response<MoviesResponse>
            ) {
                observingMovies.value = response.body()
            }

            override fun onFailure(call: Call<MoviesResponse>, @NonNull t: Throwable) = t.printStackTrace()
        })
    }

    fun getNowPlayingMovies(observingMovies: MutableLiveData<MoviesResponse>) {
        apiService.getNowPlaying("1fb92cebaf8f7372063847bb4ffe16e9")
            .enqueue(object : retrofit2.Callback<MoviesResponse> {
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    @NonNull response: Response<MoviesResponse>
                ) {
                    observingMovies.value = response.body()
                }

                override fun onFailure(call: Call<MoviesResponse>, @NonNull t: Throwable) = t.printStackTrace()
        })
    }

    fun getUpcomingMovies(observingMovies: MutableLiveData<MoviesResponse>) {
        apiService.getUpcoming("1fb92cebaf8f7372063847bb4ffe16e9")
            .enqueue(object : retrofit2.Callback<MoviesResponse> {
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    @NonNull response: Response<MoviesResponse>
                ) {
                    observingMovies.value = response.body()
                }

                override fun onFailure(call: Call<MoviesResponse>, @NonNull t: Throwable) = t.printStackTrace()
         })
    }

    fun getTopMovies(observingMovies: MutableLiveData<MoviesResponse>) {
        apiService.getTop("1fb92cebaf8f7372063847bb4ffe16e9")
            .enqueue(object : retrofit2.Callback<MoviesResponse> {
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    @NonNull response: Response<MoviesResponse>
                ) {
                    observingMovies.value = response.body()
                }

                override fun onFailure(call: Call<MoviesResponse>, @NonNull t: Throwable) = t.printStackTrace()
         })
    }

}