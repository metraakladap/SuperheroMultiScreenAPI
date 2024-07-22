package com.example.superheroslistmultiscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class DetailsFragment : Fragment() {
    private lateinit var imageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var fullNameTextView: TextView
    private lateinit var publisherTextView: TextView
    private lateinit var firstAppearanceTextView: TextView
    private lateinit var powerstatsTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        imageView = view.findViewById(R.id.superhero_image)
        nameTextView = view.findViewById(R.id.superhero_name)
        fullNameTextView = view.findViewById(R.id.superhero_full_name)
        publisherTextView = view.findViewById(R.id.superhero_publisher)
        firstAppearanceTextView = view.findViewById(R.id.superhero_first_appearance)
        powerstatsTextView = view.findViewById(R.id.powerstats)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val superhero = (activity as? MainActivity)?.getSelectedSuperhero()
        superhero?.let {
            nameTextView.text = it.name
            fullNameTextView.text = "Full Name: ${it.biography.fullName}"
            publisherTextView.text = "Publisher: ${it.biography.publisher}"
            firstAppearanceTextView.text = "First Appearance: ${it.biography.firstAppearance}"
            powerstatsTextView.text = """
                Intelligence: ${it.powerstats.intelligence}
                Strength: ${it.powerstats.strength}
                Speed: ${it.powerstats.speed}
                Durability: ${it.powerstats.durability}
                Power: ${it.powerstats.power}
                Combat: ${it.powerstats.combat}
            """.trimIndent()
            Glide.with(this).load(it.images.lg).into(imageView)
        }
    }
}