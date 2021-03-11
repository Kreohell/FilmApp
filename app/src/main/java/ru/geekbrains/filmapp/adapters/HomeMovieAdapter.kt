package ru.geekbrains.filmapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.filmapp.adapters.viewholders.DoubleGridViewHolder
import ru.geekbrains.filmapp.adapters.viewholders.SimpleItemViewHolder
import ru.geekbrains.filmapp.adapters.viewholders.TripleGridViewHolder
import ru.geekbrains.filmapp.models.SimpleMovie

class HomeMovieAdapter (private val layoutManager: GridLayoutManager? = null) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType {
        SMALL,
        MEDIUM,
        BIG
    }

    private val moviesList = arrayListOf<SimpleMovie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.SMALL.ordinal -> SimpleItemViewHolder(parent = parent)
            ViewType.MEDIUM.ordinal -> DoubleGridViewHolder(parent = parent)
            else -> TripleGridViewHolder(parent = parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SimpleItemViewHolder -> holder.bind(movieFullModel = moviesList[position])
            is DoubleGridViewHolder -> holder.bind(movieFullModel = moviesList[position])
            is TripleGridViewHolder -> holder.bind(movieFullModel = moviesList[position])
            else -> throw RuntimeException("Unknown ViewHolder")
        }
    }

    override fun getItemCount() = moviesList.count()

    override fun getItemViewType(position: Int): Int {
        return when (layoutManager?.spanCount) {
            1 -> ViewType.SMALL.ordinal
            2 -> ViewType.MEDIUM.ordinal
            else -> ViewType.BIG.ordinal
        }
    }

    fun getItemItemAt(position: Int) = moviesList[position]

    fun addMovies(movies: List<SimpleMovie>) = moviesList.addAll(movies)

    fun clearMovies() = moviesList.clear()

    fun deleteItem(position: Int) {
        moviesList.removeAt(position)
        notifyItemRemoved(position)
    }

}