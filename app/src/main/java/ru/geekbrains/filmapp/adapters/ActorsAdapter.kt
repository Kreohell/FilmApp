package ru.geekbrains.filmapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import ru.geekbrains.filmapp.R
import ru.geekbrains.filmapp.models.Actor
import ru.geekbrains.filmapp.utils.Constants

class ActorsAdapter(private val onActorCLickListener: OnActorCLickListener) :
    RecyclerView.Adapter<ActorsAdapter.ActorsViewHolder>() {

    private val actorsList = arrayListOf<Actor>()

    inner class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val actorPhoto = itemView.findViewById<RoundedImageView>(R.id.ivActorPhoto)
        private val actorName = itemView.findViewById<TextView>(R.id.tvActorName)

        fun bindActor(actor: Actor) {

            Picasso.get().load("${Constants.POSTERS_BASE_URL}${actor.profile_path}")
                .placeholder(R.drawable.photo_placeholder)
                .into(actorPhoto)

            actorName.text = actor.name.replace(" ", "\n")
            itemView.setOnClickListener {
                onActorCLickListener.onItemCLick(actor = actor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ActorsViewHolder(
        itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.actors_item, parent, false)
    )

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) =
        holder.bindActor(actor = actorsList[position])

    override fun getItemCount() = actorsList.count()

    fun addItems(actors: List<Actor>) = actorsList.addAll(actors)

    fun clearItems() = actorsList.clear()

}