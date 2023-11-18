package com.klimpel.abschlussarbeitmodul3.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klimpel.abschlussarbeitmodul3.data.models.PokemonGrindEntry
import com.klimpel.abschlussarbeitmodul3.repository.PokemonRepository
import com.klimpel.abschlussarbeitmodul3.util.parsePokemonNameToEnglish
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.w3c.dom.Text
import javax.inject.Inject

@HiltViewModel
class SearchingViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _ownedPokemon = MutableStateFlow(repository.ownedPokemonList.value)
    @OptIn(FlowPreview::class)
    val ownedPokemon = searchText
        .debounce(1000)
        .onEach { _isSearching.update { true } }
        .combine(_ownedPokemon){ text, ownedPokemon ->
            if (text.isBlank()){
                ownedPokemon
            }else{
                delay(2000)
                ownedPokemon.filter {
                    it.doesMatchSearchQuery(parsePokemonNameToEnglish(text.lowercase())) }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _ownedPokemon.value
        )

    fun onSearchTextChange(text: String){
        _searchText.value = text
    }

}