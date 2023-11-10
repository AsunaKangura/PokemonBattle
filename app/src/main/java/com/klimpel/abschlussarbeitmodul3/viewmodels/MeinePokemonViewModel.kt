package com.klimpel.abschlussarbeitmodul3.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon
import com.klimpel.abschlussarbeitmodul3.data.models.PokedexListEntry
import com.klimpel.abschlussarbeitmodul3.data.models.PokemonGrindEntry
import com.klimpel.abschlussarbeitmodul3.repository.PokemonRepository
import com.klimpel.abschlussarbeitmodul3.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MeinePokemonViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    var pokemonUbersicht = repository.ownedPokemonList

    private var chachedPokemonList = listOf<PokemonGrindEntry>()
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)

    var pokemonList = mutableStateOf<List<PokemonGrindEntry>>(listOf())

    init {
        loadOwnedPokemon()
    }

    fun loadOwnedPokemon(){
        repository.loadOwnedPokemon()
    }

    fun searchPokemonList(query: String) {
        val listToSearch = if(isSearchStarting) {
            pokemonList.value
        } else {
            chachedPokemonList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()) {
                pokemonList.value = chachedPokemonList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {pokemon ->
               pokemon.name.contains(query.trim(), ignoreCase = true) ||
                       pokemon.id.toString() == query.trim()
            }
            if(isSearchStarting) {
                chachedPokemonList = pokemonList.value
                isSearchStarting = false
            }
            pokemonList.value = results
            isSearching.value = true
        }
    }
}