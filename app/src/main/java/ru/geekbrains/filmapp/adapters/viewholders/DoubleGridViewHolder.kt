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

class DoubleGridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val poster: ImageView = itemView.findViewById(R.id.ivPoster)
    private val movieRating: TextView = itemView.findViewById(R.id.tvRating)

    constructor(parent: ViewGroup) : this(
        LayoutInflater.from(parent.context).inflate(R.layout.double_item, parent, false)
    )

    fun bind(movieFullModel: SimpleMovie) {
        Picasso.get().load("${Constants.POSTERS_BASE_URL_SMALL}${movieFullModel.posterPath}")
            .placeholder(R.drawable.ic_poster_placeholder)
            .into(poster)

        movieRating.text = movieFullModel.movieRating.toInt().toString()
    }

}