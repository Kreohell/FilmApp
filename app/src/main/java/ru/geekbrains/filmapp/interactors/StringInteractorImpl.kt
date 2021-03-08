package ru.geekbrains.filmapp.interactors

import android.content.Context
import ru.geekbrains.filmapp.R

class StringInteractorImpl(private val context: Context): StringInteractor {
    override val textUnknown: String
        get() = context.getString(R.string.unknown)
    override val textNoDescription: String
        get() = context.getString(R.string.no_description)
}