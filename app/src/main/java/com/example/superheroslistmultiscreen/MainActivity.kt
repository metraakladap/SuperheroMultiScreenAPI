package com.example.superheroslistmultiscreen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), SuperheroSelectionListener {
    private var selectedSuperhero: Superhero? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ListFragment())
                .commit()
        }
    }

    override fun onSuperheroSelected(superhero: Superhero) {
        selectedSuperhero = superhero
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetailsFragment())
            .addToBackStack(null)
            .commit()
    }

    fun getSelectedSuperhero(): Superhero? {
        return selectedSuperhero
    }
}

interface SuperheroSelectionListener {
    fun onSuperheroSelected(superhero: Superhero)
}