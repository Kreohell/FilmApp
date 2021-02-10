package ru.geekbrains.filmapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.geekbrains.filmapp.R
import ru.geekbrains.filmapp.models.Movie

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.MoviesViewHolder>(){

    private val moviesList = arrayListOf<Movie>()

    inner class MoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val poster: ImageView = itemView.findViewById(R.id.ivPoster)

        fun bindMovie(movie: Movie) = Picasso.get().load(R.drawable.ic_stat_name).into(poster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  MoviesViewHolder(itemView = LayoutInflater.from(parent.context).inflate(R.layout.movies_list_item, parent, false))

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) = holder.bindMovie(moviesList[position])

    override fun getItemCount() = moviesList.count()

    fun addItems(movies: List<Movie>) = this.moviesList.addAll(movies)

    fun clearItems() = this.moviesList.clear()
}