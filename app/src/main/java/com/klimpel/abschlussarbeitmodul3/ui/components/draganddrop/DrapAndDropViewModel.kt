package com.klimpel.abschlussarbeitmodul3.ui.components.draganddrop

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.klimpel.abschlussarbeitmodul3.data.models.PokemonGrindEntry
import com.klimpel.abschlussarbeitmodul3.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrapAndDropViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    var isCurrentlyDragging by mutableStateOf(false)
        private set

    var pokemonlist by mutableStateOf(emptyList<PokemonGrindEntry>())
        private set

    var addedPersons = mutableStateListOf<PokemonGrindEntry>()
        private set

    fun startDragging(){
        isCurrentlyDragging = true
    }
    fun stopDragging(){
        isCurrentlyDragging = false
    }

    fun addPokemon(pokemonGrindEntry: PokemonGrindEntry){
        println("Added Person")
        addedPersons.add(pokemonGrindEntry)
    }
}