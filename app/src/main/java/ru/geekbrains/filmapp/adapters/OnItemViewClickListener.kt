package ru.geekbrains.filmapp.adapters

import ru.geekbrains.filmapp.models.Movie

interface OnItemViewClickListener {
    fun onItemClick(movie: Movie)
}