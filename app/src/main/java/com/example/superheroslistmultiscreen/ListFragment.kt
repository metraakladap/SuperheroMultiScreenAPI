package com.example.superheroslistmultiscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class ListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SuperheroAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = SuperheroAdapter { superhero ->
            (activity as? SuperheroSelectionListener)?.onSuperheroSelected(superhero)
        }
        recyclerView.adapter = adapter
        loadSuperheros()
        return view
    }

    private fun loadSuperheros() {
        lifecycleScope.launch {
            try {
                val superheros = MyApplication.getApp().repo.getSuperheros()
                adapter.submitList(superheros)
            } catch (error: Exception) {
                Log.e("ListFragment", "Error loading superheros", error)
            }
        }
    }
}
