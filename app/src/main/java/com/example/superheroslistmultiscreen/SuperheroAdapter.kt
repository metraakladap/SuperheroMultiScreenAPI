package com.example.superheroslistmultiscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SuperheroAdapter(private val onItemClick: (Superhero) -> Unit) :
    ListAdapter<Superhero, SuperheroAdapter.SuperheroViewHolder>(SuperheroDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_superhero, parent, false)
        return SuperheroViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SuperheroViewHolder(itemView: View, private val onItemClick: (Superhero) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.superhero_name)
        private val publisherTextView: TextView = itemView.findViewById(R.id.superhero_publisher)
        private val imageView: ImageView = itemView.findViewById(R.id.superhero_image)

        fun bind(superhero: Superhero) {
            nameTextView.text = superhero.name
            publisherTextView.text = superhero.biography.publisher
            Glide.with(itemView).load(superhero.images.sm).into(imageView)
            itemView.setOnClickListener { onItemClick(superhero) }
        }
    }

    class SuperheroDiffCallback : DiffUtil.ItemCallback<Superhero>() {
        override fun areItemsTheSame(oldItem: Superhero, newItem: Superhero): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Superhero, newItem: Superhero): Boolean {
            return oldItem == newItem
        }
    }
}