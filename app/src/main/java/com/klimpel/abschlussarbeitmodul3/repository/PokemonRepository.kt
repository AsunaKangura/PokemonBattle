package com.klimpel.abschlussarbeitmodul3.repository

import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon
import com.asunakangura.pokemonbattle.data.remote.responses.PokemonList
import com.klimpel.abschlussarbeitmodul3.data.models.Avatar
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.data.models.PokemonGrindEntry
import com.klimpel.abschlussarbeitmodul3.data.models.StroreCategoryItem
import com.klimpel.abschlussarbeitmodul3.data.remote.PokeApi
import com.klimpel.abschlussarbeitmodul3.util.Resource
import com.klimpel.pokemonbattlefinal.R
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
) {
    // Firebase repository instance
    val firebase = FirebaseRepository()

    // LiveData of the logged-in user
    val currentUser get() = firebase.currentUser

    // Current Team Flow
    val currentTeam: StateFlow<BattleTeams> get() = firebase.currentTeam

    val addTeam: StateFlow<BattleTeams> get() = firebase.addteam

    // Current TeamList Flow
    val teamList: StateFlow<List<BattleTeams>> get() = firebase.teamList

    // List of owned Pokemon
    val ownedPokemonList: StateFlow<List<PokemonGrindEntry>> get() = firebase.ownedPokemonList

    // List of store categories
    val storecategoryList: StateFlow<MutableList<StroreCategoryItem>> get() = firebase.loadStoreCategory

    init {
        loadListOfAvatars()
    }

    /**
     * Fetches a list of Pokemon from the API.
     * @param limit The maximum number of Pokemon to fetch.
     * @param offset The offset for pagination.
     * @return A resource containing the fetched Pokemon list or an error message.
     */
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }

    /**
     * Fetches information about a specific Pokemon from the API.
     * @param pokemonName The name of the Pokemon.
     * @return A resource containing the fetched Pokemon information or an error message.
     */
    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonInfo(pokemonName)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }

    /**
     * Finds an avatar by its name.
     * @param name The name of the avatar.
     * @return The avatar object if found, null otherwise.
     */
    fun findAvatar(name: String): Avatar? {
        // Create a list of Avatar objects with corresponding names and resource IDs.
        val avatars = listOf(
            Avatar("default", R.drawable.ic_person),
            Avatar("evoli", R.drawable.evoli),
            Avatar("rattfratz", R.drawable.rattfratz),
            Avatar("abra", R.drawable.abra),
            Avatar("menki", R.drawable.menki),
            Avatar("pummeluff", R.drawable.pummeluff),
            Avatar("bisasam", R.drawable.bisasam),
            Avatar("knofensa", R.drawable.knofensa),
            Avatar("ponita", R.drawable.ponita),
            Avatar("enton", R.drawable.enton),
            Avatar("marill", R.drawable.marill),
            Avatar("mauzi", R.drawable.mauzi),
            Avatar("schiggy", R.drawable.schiggy),
            Avatar("taubsi", R.drawable.taubsi),
            Avatar("pikachu", R.drawable.pikachu),
        )

        // Search for the avatar in the list based on the name and return it.
        return avatars.find { it.name == name }
    }

    /**
     * Finds the resource ID of an avatar by its name.
     * @param name The name of the avatar.
     * @return The resource ID of the avatar if found, -1 otherwise.
     */
    fun findAvatarInt(name: String): Int {
        // Create a list of Avatar objects with corresponding names and resource IDs.
        val avatars = listOf(
            Avatar("default", R.drawable.ic_person),
            Avatar("evoli", R.drawable.evoli),
            Avatar("rattfratz", R.drawable.rattfratz),
            Avatar("abra", R.drawable.abra),
            Avatar("menki", R.drawable.menki),
            Avatar("pummeluff", R.drawable.pummeluff),
            Avatar("bisasam", R.drawable.bisasam),
            Avatar("knofensa", R.drawable.knofensa),
            Avatar("ponita", R.drawable.ponita),
            Avatar("enton", R.drawable.enton),
            Avatar("marill", R.drawable.marill),
            Avatar("mauzi", R.drawable.mauzi),
            Avatar("schiggy", R.drawable.schiggy),
            Avatar("taubsi", R.drawable.taubsi),
            Avatar("pikachu", R.drawable.pikachu),
        )

        // Search for the avatar in the list based on the name and return its resource ID.
        val avatar = avatars.find { it.name == name }
        return avatar?.imageResource ?: -1
    }

    /**
     * Loads a list of avatars.
     * @return The list of avatars.
     */
    fun loadListOfAvatars(): List<Avatar> {
        return listOf(
            Avatar("evoli", R.drawable.evoli),
            Avatar("rattfratz", R.drawable.rattfratz),
            Avatar("abra", R.drawable.abra),
            Avatar("menki", R.drawable.menki),
            Avatar("pummeluff", R.drawable.pummeluff),
            Avatar("bisasam", R.drawable.bisasam),
            Avatar("knofensa", R.drawable.knofensa),
            Avatar("enton", R.drawable.enton),
            Avatar("marill", R.drawable.marill),
            Avatar("mauzi", R.drawable.mauzi),
            Avatar("schiggy", R.drawable.schiggy),
            Avatar("taubsi", R.drawable.taubsi),
            Avatar("pikachu", R.drawable.pikachu),
        )
    }
}

