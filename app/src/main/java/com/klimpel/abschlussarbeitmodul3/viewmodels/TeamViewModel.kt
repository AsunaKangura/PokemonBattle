package com.klimpel.abschlussarbeitmodul3.viewmodels

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

    fun deleteCurrentTeam(){
        repository.deleteCurrentTeam()
    }

    fun getTeamInfo(id: String){
        repository.getTeam(id)
    }

    fun loadTeamubersicht(){
        repository.loadTeamList()
    }

}