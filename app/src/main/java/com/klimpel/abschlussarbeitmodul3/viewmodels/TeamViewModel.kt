package com.klimpel.abschlussarbeitmodul3.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon
import com.klimpel.abschlussarbeitmodul3.repository.PokemonRepository
import com.klimpel.abschlussarbeitmodul3.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    val currentUser = repository.currentUser

    var currentTeam = repository.currentTeam
    var teamubersicht = repository.teamList


    fun addTeam(context: Context){
        repository.addTeam(context)
    }

    fun loadAktivTeam(){
        repository.loadAktivTeam()
    }

    fun deleteAktivTeam(context: Context){
        repository.deleteAktivTeam(context)
    }

    fun updateTeam(context: Context){
        repository.updateTeam(context)
    }

    fun updatePokemonTeam(context: Context, id: Int, pokemonName: String){
        repository.updatePokemonTeam(context, id, pokemonName )
    }

    fun deleteTeam(context: Context, teamId: String){
        repository.deleteTeam(context, teamId)
    }

    fun deleteCurrentTeam(){
        repository.deleteCurrentTeam()
    }

    fun deldeletePokemoninTeam(context: Context, pokeID: Int, teamID: String){
        repository.deletePokemoninTeam(context, pokeID, teamID)
    }

    fun getTeamInfo(id: String){
        repository.getTeam(id)
    }

    fun loadTeamubersicht(){
        repository.loadTeamList()
    }

}