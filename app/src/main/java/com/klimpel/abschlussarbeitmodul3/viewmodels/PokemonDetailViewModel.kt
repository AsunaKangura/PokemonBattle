package com.klimpel.abschlussarbeitmodul3.viewmodels

import androidx.lifecycle.ViewModel
import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon
import com.klimpel.abschlussarbeitmodul3.repository.PokemonRepository
import com.klimpel.abschlussarbeitmodul3.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel class for managing Pokemon detail information.
 *
 * @param repository The PokemonRepository instance.
 */
@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {
    /**
     * Retrieves the detailed information of a Pokemon.
     *
     * @param pokemonName The name of the Pokemon.
     * @return A Resource object containing the Pokemon information.
     */
    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return repository.getPokemonInfo(pokemonName)
    }
}