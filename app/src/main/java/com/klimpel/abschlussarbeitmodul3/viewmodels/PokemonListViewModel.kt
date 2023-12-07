package com.klimpel.abschlussarbeitmodul3.viewmodels


import android.nfc.tech.MifareUltralight
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klimpel.abschlussarbeitmodul3.data.models.PokedexListEntry
import com.klimpel.abschlussarbeitmodul3.repository.PokemonRepository
import com.klimpel.abschlussarbeitmodul3.util.Contants.Companion.LIMITS
import com.klimpel.abschlussarbeitmodul3.util.Contants.Companion.OFFSET
import com.klimpel.abschlussarbeitmodul3.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private var curPage = 0
    private var chachedPokemonList = listOf<PokedexListEntry>()
    private var isSearchStarting = true

    /**
     * The list of Pokemon in the current page.
     */
    var pokemonList = mutableStateOf<List<PokedexListEntry>>(listOf())

    /**
     * The error message when loading the Pokemon list.
     */
    var loadError = mutableStateOf("")

    /**
     * Flag indicating if the Pokemon list is currently being loaded.
     */
    var isLoading = mutableStateOf(false)

    /**
     * Flag indicating if the end of the Pokemon list has been reached.
     */
    var endReached = mutableStateOf(false)

    /**
     * Flag indicating if a search operation is currently in progress.
     */
    var isSearching = mutableStateOf(false)

    init {
        loadPokemonPaginated()
    }

    /**
     * Searches the Pokemon list based on the given query.
     *
     * @param query The search query.
     */
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
            val results = listToSearch.filter {
                it.pokemonName.contains(query.trim(), ignoreCase = true) ||
                        it.number.toString() == query.trim()
            }
            if(isSearchStarting) {
                chachedPokemonList = pokemonList.value
                isSearchStarting = false
            }
            pokemonList.value = results
            isSearching.value = true
        }
    }

    /**
     * Loads the next page of the Pokemon list.
     */
    fun loadPokemonPaginated() {
        viewModelScope.launch {
            isLoading.value = true
            when(val result = repository.getPokemonList(LIMITS, OFFSET)) {
                is Resource.Success -> {
                    endReached.value = curPage * MifareUltralight.PAGE_SIZE >= result.data!!.count
                    val pokedexEntries = result.data.results.mapIndexed { index, entry ->
                        val number = if(entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }
                        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokedexListEntry(entry.name, url,number.toInt())
                    }
                    curPage++

                    loadError.value = ""
                    isLoading.value = false
                    pokemonList.value += pokedexEntries
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }

                else -> {}
            }
        }
    }

}