package com.klimpel.abschlussarbeitmodul3.viewmodels

import androidx.lifecycle.ViewModel
import com.klimpel.abschlussarbeitmodul3.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MeinePokemonViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    var pokemonUbersicht = repository.ownedPokemonList


    fun loadOwnedPokemon(){
        repository.loadOwnedPokemon()
    }


}