package ru.geekbrains.filmapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.geekbrains.filmapp.R
import ru.geekbrains.filmapp.models.Movie

class SearchMovieAdapter : RecyclerView.Adapter<SearchMovieAdapter.SearchMoviesViewHolder>() {

    private val moviesList = arrayListOf<Movie>()

    inner class SearchMoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val poster: ImageView = itemView.findViewById(R.id.ivSearchMoviePoster)

        fun bindMovie(movie: Movie) {
            if (movie.poster_path != null) {
                Picasso.get().load(R.drawable.ic_stat_name).into(poster)
            } else {
                poster.setImageResource(R.drawable.ic_poster_placeholder)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  SearchMoviesViewHolder(itemView = LayoutInflater.from(parent.context).inflate(R.layout.movies_search_item, parent, false))

    override fun onBindViewHolder(holder: SearchMoviesViewHolder, position: Int) = holder.bindMovie(moviesList[position])

    override fun getItemCount() = moviesList.count()

    fun addItems(movies: List<Movie>) = this.moviesList.addAll(movies)

    fun clearItems() = this.moviesList.clear()

}