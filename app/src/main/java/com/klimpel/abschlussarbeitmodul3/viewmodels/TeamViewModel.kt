package com.klimpel.abschlussarbeitmodul3.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.repository.PokemonRepository
import com.klimpel.abschlussarbeitmodul3.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    // Current user from repository
    val currentUser = repository.currentUser

    // Add team state from repository
    var addTeam = repository.addTeam

    // Current team from repository
    val currentTeam = repository.currentTeam

    // Owned Pokemon list from repository
    var pokemonUbersicht = repository.ownedPokemonList

    init {
        loadOwnedPokemon()
    }

    /**
     * Load the owned Pokemon list from Firebase.
     */
    private fun loadOwnedPokemon(){
        repository.firebase.loadOwnedPokemon()
    }

    /**
     * Load a team with the given name from Firebase.
     * @param name The name of the team to load.
     */
    fun loadTeam(name: String) {
        repository.firebase.loadTeam(name)
    }

    /**
     * Add a Pokemon to the current team.
     * @param name The name of the Pokemon to add.
     * @param id The ID of the Pokemon to add.
     */
    fun addPokemonToTeam(name: String, id: Int) {
        repository.firebase.pokemonHinzufugenAddTeam(name, id)
    }

    /**
     * Add a team name to the addTeam state.
     * @param name The name of the team to add.
     */
    fun addTeamName(name: String) {
        repository.firebase.teamNameHinzufugenAddTeam(name)
    }

    /**
     * Add a team to Firebase.
     * @param context The context.
     * @param navController The NavController.
     */
    fun addTeam(context: Context, navController: NavController) {
        repository.firebase.teamHinzufugen(context, navController)
    }

    /**
     * Delete a team from Firebase.
     * @param context The context.
     * @param battleTeams The BattleTeams object representing the team to delete.
     */
    fun deleteTeam(context: Context, battleTeams: BattleTeams) {
        repository.firebase.deleteTeam(context, battleTeams)
    }

    /**
     * Delete the current team from Firebase.
     */
    fun deleteCurrentTeam(){
        repository.firebase.deleteCurrentTeam()
    }

    /**
     * Get information about a Pokemon.
     * @param pokemonName The name of the Pokemon.
     * @return A Resource object containing the Pokemon information.
     */
    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return repository.getPokemonInfo(pokemonName)
    }

}