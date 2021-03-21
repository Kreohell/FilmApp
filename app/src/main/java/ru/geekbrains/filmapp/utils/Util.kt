package ru.geekbrains.filmapp.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import ru.geekbrains.filmapp.R
import ru.geekbrains.filmapp.models.ProductionCountries

fun getDuration(runtime: Int?): String {
    runtime?.let {
        val hours = it / 60
        val minutes = it % 60
        return String.format("%dh %02dmin", hours, minutes)
    } ?: return "Unknown"
}

fun getCountry(countries: List<ProductionCountries>): String {
    if (countries.count() == 0) return "Unknown"
    return countries.first().name
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun selectMapLink(textView: TextView, context: Context) =
    textView.setTextColor(ContextCompat.getColor(context, R.color.white))

fun unselectedMapLink(textView: TextView, context: Context) =
    textView.setTextColor(ContextCompat.getColor(context, R.color.actorBirthText))

fun observeFavorite(imageView: ImageView, favorite: Boolean) {
    imageView.apply {
        when {
            favorite -> setImageResource(R.drawable.ic_favorite)
            else -> setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }
}