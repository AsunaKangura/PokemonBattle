package com.klimpel.abschlussarbeitmodul3.viewmodels

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskCompletionSource
import com.google.android.play.core.integrity.p
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.data.models.PokedexListEntry
import com.klimpel.abschlussarbeitmodul3.data.models.PokemonGrindEntry
import com.klimpel.abschlussarbeitmodul3.repository.PokemonRepository
import com.klimpel.abschlussarbeitmodul3.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    val currentUser = repository.currentUser
    var addTeam = repository.addTeam
    val currentTeam = repository.currentTeam

    var pokemonUbersicht = repository.ownedPokemonList

    init {
        loadOwnedPokemon()
    }

    private fun loadOwnedPokemon(){
        repository.firebase.loadOwnedPokemon()
    }

    fun loadTeam(name: String) {
        repository.firebase.loadTeam(name)
    }

    fun loadTeam2(name: String){
       repository.firebase.loadTeam(name)
    }

    fun pokemonHinzufugenAddTeam(name: String, id: Int) {
        repository.firebase.pokemonHinzufugenAddTeam(name, id)
    }

    fun teamnamehinzufugenAddTeam(name: String) {
        repository.firebase.teamnamehinzufugenAddTeam(name)
    }

    fun teamhinzufugen(context: Context) {
        repository.firebase.teamhinzufugen(context)
    }

    fun deleteTeam(context: Context, battleTeams: BattleTeams) {
        repository.firebase.deleteTeam(context, battleTeams)
    }

    fun deleteCurrentTeam(){
        repository.firebase.deleteCurrentTeam()
    }

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return repository.getPokemonInfo(pokemonName)
    }

}