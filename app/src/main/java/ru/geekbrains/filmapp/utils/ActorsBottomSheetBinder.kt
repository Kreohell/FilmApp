package ru.geekbrains.filmapp.utils

import com.squareup.picasso.Picasso
import ru.geekbrains.filmapp.R
import ru.geekbrains.filmapp.databinding.ActorBottomSheetBinding
import ru.geekbrains.filmapp.models.ActorFullInfoModel

class ActorsBottomSheetBinder {

    fun bindActor(actorBottomSheet: ActorBottomSheetBinding, actor: ActorFullInfoModel) {
        actorBottomSheet.tvActorFullName.text = actor.name
        actorBottomSheet.tvActorBiography.text = actor.biography
        actorBottomSheet.tvBirthday.text = actor.birthday
        actorBottomSheet.tvPlaceOfBirth.text = actor.placeOfBirth
        Picasso.get().load("${Constants.POSTERS_BASE_URL}${actor.photo}")
            .placeholder(R.drawable.photo_placeholder)
            .into(actorBottomSheet.ivActorFullPhoto)
    }

}