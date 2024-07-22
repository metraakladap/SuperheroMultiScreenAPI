package com.example.superheroslistmultiscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SuperheroAdapter
    private val disposable = CompositeDisposable()

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
        disposable.add(
        RetrofitClient.getApi().getSuperheros()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ superheros ->
                adapter.submitList(superheros)
            }, { error ->
                Log.e("ListFragment", "Error loading superheros", error)
            })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}