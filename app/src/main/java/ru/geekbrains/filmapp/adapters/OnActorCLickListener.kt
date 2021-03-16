package ru.geekbrains.filmapp.adapters

import ru.geekbrains.filmapp.models.Actor

interface OnActorCLickListener {
    fun onItemCLick(actor: Actor)
}