package com.example.superheroslistmultiscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuperheroViewModel @Inject constructor(
    private val repository: SuperheroRepository
) : ViewModel() {

    private val _superheros = MutableLiveData<List<Superhero>>()
    val superheros: LiveData<List<Superhero>> = _superheros

    private val _selectedSuperhero = MutableLiveData<Superhero>()
    val selectedSuperhero: LiveData<Superhero> = _selectedSuperhero

    fun loadSuperheros() {
        viewModelScope.launch {
            try {
                _superheros.value = repository.getSuperheros()
            } catch (e: Exception) {
                Log.e("SuperheroViewModel", "Error loading superheros", e)
                delay(2000)
                loadSuperheros()
            }
        }
    }

    fun selectSuperhero(superhero: Superhero) {
        _selectedSuperhero.value = superhero
    }
}