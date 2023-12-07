package com.klimpel.abschlussarbeitmodul3.viewmodels

import androidx.lifecycle.ViewModel
import com.klimpel.abschlussarbeitmodul3.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel class for managing owned Pokemon data.
 *
 * @param repository The PokemonRepository instance.
 */
@HiltViewModel
class MeinePokemonViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {
    /**
     * LiveData object representing the owned Pokemon list.
     */
    val pokemonUbersicht = repository.ownedPokemonList

    /**
     * Loads the owned Pokemon data from Firebase.
     */
    fun loadOwnedPokemon() {
        repository.firebase.loadOwnedPokemon()
    }
}