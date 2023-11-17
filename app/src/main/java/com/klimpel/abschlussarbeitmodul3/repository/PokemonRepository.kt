package com.klimpel.abschlussarbeitmodul3.repository

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon
import com.asunakangura.pokemonbattle.data.remote.responses.PokemonList
import com.klimpel.abschlussarbeitmodul3.data.models.Avatar
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.data.models.PokemonGrindEntry
import com.klimpel.abschlussarbeitmodul3.data.models.PokemonTeamCard
import com.klimpel.abschlussarbeitmodul3.data.models.User
import com.klimpel.abschlussarbeitmodul3.data.remote.PokeApi
import com.klimpel.abschlussarbeitmodul3.ui.components.messageDialogError
import com.klimpel.abschlussarbeitmodul3.ui.components.messageDialogSuccess
import com.klimpel.abschlussarbeitmodul3.util.Resource
import com.klimpel.abschlussarbeitmodul3.util.Contants.Companion.firestore
import com.klimpel.abschlussarbeitmodul3.util.generatePokemonAlias
import com.klimpel.pokemonbattlefinal.R
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
) {
    val firebase = FirebaseRepository()

    //LiveData von dem eingeloggten User
    val currentUser get() = firebase.currentUser

    // Current Team Flow
    val currentTeam: StateFlow<BattleTeams> get() = firebase.currentTeam
    val addTeam: StateFlow<BattleTeams> get() = firebase.addteam

    // Current TeamList Flow
    val teamList: StateFlow<List<BattleTeams>> get() = firebase.teamList

    // Laden der eigenen Pokemon
    val ownedPokemonList: StateFlow<List<PokemonGrindEntry>> get() = firebase.ownedPokemonList


    init {
        loadListOfAvatars()
    }

    // TODO: Ab hier keine Firebase Funktionen
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonInfo(pokemonName)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }

    fun findAvatar(name: String): Avatar? {
        // Erstelle eine Liste von Avatar-Objekten mit den entsprechenden Namen und Ressourcen-IDs.
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
        // Suche nach dem Avatar in der Liste anhand des Namens und gib ihn zurück.
        return avatars.find { it.name == name }
    }

    fun findAvatarInt(name: String): Int {
        // Erstelle eine Liste von Avatar-Objekten mit den entsprechenden Namen und Ressourcen-IDs.
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
        ).find { it.name == name }
        // Suche nach dem Avatar in der Liste anhand des Namens und gib ihn zurück.
        if (avatars != null) {
            return avatars.imageResource
        }
        return -1
    }

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