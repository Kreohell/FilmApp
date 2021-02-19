package ru.geekbrains.filmapp.services

import android.app.IntentService
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import ru.geekbrains.filmapp.response.MoviesResponse
import ru.geekbrains.filmapp.utils.Constants
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection


class MoviesService (name: String = "MoviesService") : IntentService(name) {
    private val broadcastIntent = Intent("INTENT FILTER")

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onHandleIntent(intent: Intent?) {
        if (intent == null) return
        val movie = intent.getStringExtra("Movie")
        if (movie == "") return
        loadData(movie)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadData(movie: String?) {
        try {
            val uri = URL("${Constants.BASE_URL}/3/search/movie?api_key=${"1fb92cebaf8f7372063847bb4ffe16e9"}&language=${Constants.locale}&query=$movie")
            lateinit var urlConnection: HttpsURLConnection
            try {
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.apply {
                    requestMethod = "GET"
                    readTimeout = 10000
                }
                val movieResponse: MoviesResponse =
                        Gson().fromJson(
                                getLines(BufferedReader(InputStreamReader(urlConnection.inputStream))),
                                MoviesResponse::class.java
                        )
                onResponse(movieResponse)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                urlConnection.disconnect()
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader) =
            reader.lines().collect(Collectors.joining("\n"))


    private fun onResponse(movieResponse: MoviesResponse) {
        broadcastIntent.putExtra("movieResponse", movieResponse)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }
}