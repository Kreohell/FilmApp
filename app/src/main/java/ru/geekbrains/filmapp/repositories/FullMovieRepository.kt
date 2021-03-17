package ru.geekbrains.filmapp.repositories

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Response
import ru.geekbrains.filmapp.BuildConfig
import ru.geekbrains.filmapp.api.ApiService
import ru.geekbrains.filmapp.models.ActorFullInfoModel
import ru.geekbrains.filmapp.models.MovieFullModel
import ru.geekbrains.filmapp.net.RetrofitInstance
import ru.geekbrains.filmapp.response.CastResponse

class FullMovieRepository {
    private var apiService: ApiService = RetrofitInstance.api

    fun getMovieInfo(_observingMovie: MutableLiveData<MovieFullModel>, id: Int) {
        apiService.getMovieDetails(id = id, "1fb92cebaf8f7372063847bb4ffe16e9").enqueue(object :
            retrofit2.Callback<MovieFullModel> {
            override fun onResponse(
                call: Call<MovieFullModel>,
                response: Response<MovieFullModel>
            ) {
                _observingMovie.value = response.body()
            }

            override fun onFailure(call: Call<MovieFullModel>, t: Throwable) = t.printStackTrace()
        })
    }

    fun getCast(_observingActors: MutableLiveData<CastResponse>, id: Int) {
        apiService.getMovieCast(id = id, "1fb92cebaf8f7372063847bb4ffe16e9")
            .enqueue(object : retrofit2.Callback<CastResponse> {
                override fun onResponse(
                    call: Call<CastResponse>,
                    response: Response<CastResponse>
                ) {
                    _observingActors.value = response.body()
                }

                override fun onFailure(call: Call<CastResponse>, t: Throwable) = t.printStackTrace()
            })
    }

    fun getActorInfo(actorId: Int, observer: MutableLiveData<ActorFullInfoModel>) {
        apiService.getActor(actorId, "1fb92cebaf8f7372063847bb4ffe16e9")
            .enqueue(object : retrofit2.Callback<ActorFullInfoModel> {
                override fun onResponse(
                    call: Call<ActorFullInfoModel>,
                    response: Response<ActorFullInfoModel>
                ) {
                    observer.value = response.body()
                }

                override fun onFailure(call: Call<ActorFullInfoModel>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}