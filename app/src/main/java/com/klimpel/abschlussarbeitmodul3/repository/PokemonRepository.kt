package com.klimpel.abschlussarbeitmodul3.repository

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon
import com.asunakangura.pokemonbattle.data.remote.responses.PokemonList
import com.klimpel.abschlussarbeitmodul3.data.models.Avatar
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.data.models.PokedexListEntry
import com.klimpel.abschlussarbeitmodul3.data.models.PokemonGrindEntry
import com.klimpel.abschlussarbeitmodul3.data.models.User
import com.klimpel.abschlussarbeitmodul3.data.remote.PokeApi
import com.klimpel.abschlussarbeitmodul3.ui.components.messageDialogSuccess
import com.klimpel.abschlussarbeitmodul3.util.Resource
import com.klimpel.abschlussarbeitmodul3.util.Contants.Companion.firestore
import com.klimpel.abschlussarbeitmodul3.util.STARTER_TEAM
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
    //LiveData von dem eingeloggten User
    var currentUser by mutableStateOf<User?>(null)

    // Current Team Flow
    private val _currentTeam = MutableStateFlow(STARTER_TEAM)
    var currentTeam: StateFlow<BattleTeams> = _currentTeam.asStateFlow()

    // Current TeamList
    var currentTeamList by mutableStateOf<MutableList<String>>(mutableListOf())
    private var _teamList = MutableStateFlow(currentTeamList)
    var teamList: StateFlow<List<String>> = _teamList.asStateFlow()

    // Laden der eigenen Pokemon
    var PokemonList by mutableStateOf<MutableList<PokemonGrindEntry>>(mutableListOf())
    private var _ownedPokemonList = MutableStateFlow(PokemonList)
    var ownedPokemonList: StateFlow<List<PokemonGrindEntry>> = _ownedPokemonList.asStateFlow()

    fun loadOwnedPokemon(){
        firestore.collection("user")
            .document(currentUser?.id.toString())
            .collection("meinepokemon")
            .get()
            .addOnSuccessListener{result->
                val pokemonList = mutableListOf<PokemonGrindEntry>()
                for (document in result){
                    pokemonList.add(
                        PokemonGrindEntry(
                            document.data["name"].toString(),
                            document.data["anzahl"].toString().toInt(),
                            document.data["collectedexp"].toString().toInt(),
                            document.data["level"].toString().toInt(),
                            document.data["id"].toString().toInt()
                        )
                    )
                }
                _ownedPokemonList.value = pokemonList
            }
            .addOnFailureListener {

            }
    }

    fun loadTeamList() {
        firestore.collection("user")
            .document(currentUser?.id.toString())
            .collection("teams")
            .get()
            .addOnSuccessListener {result ->
                val teamlist = mutableListOf<String>()
                for (document in result){
                    teamlist.add(document.id)
                }
                _teamList.value = teamlist
            }
            .addOnFailureListener {
                Log.e("REPOSITORY", "Error getting documents: ")
            }
    }

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

    fun deleteCurrentTeam(){
        _currentTeam.value = BattleTeams("","","","")
    }

    fun getTeam(id: String) : BattleTeams {
        firestore.collection("user")
            .document(currentUser?.id.toString())
            .collection("teams")
            .document(id)
            .get()
            .addOnSuccessListener {result ->
                _currentTeam.value = BattleTeams(
                    result.data?.get("teamname").toString(),
                    result.data?.get("pokemon1").toString(),
                    result.data?.get("pokemon2").toString(),
                    result.data?.get("pokemon3").toString()
                )
                Log.e("Repository", "${currentTeam}")
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
            }
        return currentTeam.value ?: BattleTeams("","","","")
    }

    fun updateCurrentUser(id: String) {
        // Firestore-Dokument mit der angegebenen ID abrufen
        firestore.collection("user").document(id)
            .get()
            .addOnSuccessListener { result ->
                // Benutzerobjekt mit den abgerufenen Daten erstellen
                currentUser = User(
                    result.id,
                    result.data?.get("alias").toString(),
                    result.data?.get("pokedollar").toString().toInt(),
                    result.data?.get("avatar").toString(),
                    result.data?.get("teams").toString().toInt()
                )
            }
            .addOnFailureListener {
                // Fehlerbehandlung bei Fehlschlag des Abrufs
                Log.e("LOADUSER_FEHLER", "$it")
            }
    }

    fun updateFireStoreUser(context: Context) {
        val updatedUser = hashMapOf(
            "alias" to currentUser?.alias,
            "avatar" to currentUser?.avatar,
            "pokedollar" to currentUser?.pokedollar,
            "teams" to currentUser?.teams.toString().toInt()
        )
        firestore.collection("user").document(currentUser?.id.toString())
            .set(updatedUser)
            .addOnSuccessListener {
                messageDialogSuccess(context = context, messageText = "Änderungen wurden übernommen")
            }
            .addOnFailureListener { e -> Log.w("ALIAS_CHANGE", "Error writing document", e) }
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

    fun loadListOfAvatars() : List<Avatar>{
        return listOf (
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
    }

}