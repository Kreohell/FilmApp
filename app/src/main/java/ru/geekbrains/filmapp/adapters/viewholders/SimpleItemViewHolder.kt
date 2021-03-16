package ru.geekbrains.filmapp.adapters.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.geekbrains.filmapp.R
import ru.geekbrains.filmapp.models.SimpleMovie
import ru.geekbrains.filmapp.utils.Constants
import ru.geekbrains.filmapp.utils.getDuration

class SimpleItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val poster: ImageView = itemView.findViewById(R.id.ivPoster)
    private val movieName: TextView = itemView.findViewById(R.id.tvMovieName)
    private val movieReleaseYear: TextView = itemView.findViewById(R.id.tvReleaseYear)
    private val movieReleaseCountry: TextView = itemView.findViewById(R.id.tvReleaseCountry)
    private val movieDuration: TextView = itemView.findViewById(R.id.tvDuration)
    private val movieRating: TextView = itemView.findViewById(R.id.tvRating)

    constructor(parent: ViewGroup) : this(
        LayoutInflater.from(parent.context).inflate(R.layout.simple_item, parent, false)
    )

    fun bind(movieFullModel: SimpleMovie) {
        Picasso.get().load("${Constants.POSTERS_BASE_URL_SMALL}${movieFullModel.posterPath}")
            .placeholder(R.drawable.ic_poster_placeholder)
            .into(poster)

        movieName.text = movieFullModel.movieTitle
        movieReleaseYear.text = movieFullModel.movieReleaseDate.take(4)
        movieReleaseCountry.text = movieFullModel.movieReleaseCountry
        movieDuration.text = getDuration(movieFullModel.movieRuntime)
        movieRating.text = movieFullModel.movieRating.toInt().toString()

    }
}