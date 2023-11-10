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
    var currentUser by mutableStateOf<User?>(null)

    // Erstellen eines Teams
    //var addTeam by mutableStateOf(BattleTeams("", "", "", ""))

    // Current Team Flow
    private val _currentTeam = MutableStateFlow(BattleTeams("", "", "", ""))
    var currentTeam: StateFlow<BattleTeams> = _currentTeam.asStateFlow()

    // Current TeamList Flow
    private var currentTeamList by mutableStateOf<MutableList<PokemonTeamCard>>(mutableListOf())
    private var _teamList = MutableStateFlow(currentTeamList)
    var teamList: StateFlow<List<PokemonTeamCard>> = _teamList.asStateFlow()

    // Laden der eigenen Pokemon
    private var PokemonList by mutableStateOf<MutableList<PokemonGrindEntry>>(mutableListOf())
    private var _ownedPokemonList = MutableStateFlow(PokemonList)
    var ownedPokemonList: StateFlow<List<PokemonGrindEntry>> = _ownedPokemonList.asStateFlow()

    fun registerNewUser(context: Context, id: String){
        val registerNewUser = hashMapOf(
            "aktivteam" to "null",
            "alias" to generatePokemonAlias(),
            "avatar" to "default",
            "pokedollar" to "1000".toInt(),
            "pokemontickets" to "0".toInt(),
            "teams" to "0".toInt()
        )

        firestore.collection("user")
            .document(id)
            .set(registerNewUser)
            .addOnSuccessListener {
                messageDialogSuccess(context, "Registrierung war erfolgreich")
            }
            .addOnFailureListener {
                messageDialogError(context, "Registrierung ist fehlgeschlagen")
            }
    }

    fun loadTeamList() {
        firestore.collection("user")
            .document(currentUser?.id.toString())
            .collection("teams")
            .get()
            .addOnSuccessListener { result ->
                val teamlist = mutableListOf<PokemonTeamCard>()
                for (document in result) {
                    teamlist.add(PokemonTeamCard(document.id, document.id))
                }
                _teamList.value = teamlist
            }
            .addOnFailureListener {
                Log.e("LOAD_TEAM_LIST", "Error getting documents: ")
            }
    }

    fun addTeam(context: Context) {
        val teamerstellen = hashMapOf(
            "pokemon1" to _currentTeam.value.pokemonOne,
            "pokemon2" to _currentTeam.value.pokemonTwo,
            "pokemon3" to _currentTeam.value.pokemonThree,
            "teamname" to _currentTeam.value.teamName,
        )

        currentUser?.teams = currentUser?.teams?.plus(1)!!
        currentUser?.aktivteam = teamerstellen["teamname"].toString()

        firestore.collection("user")
            .document(currentUser?.id.toString())
            .collection("teams")
            .document(currentTeam.value.teamName)
            .set(teamerstellen)
            .addOnSuccessListener {
                updateFireStoreUser(context)
                getTeam(teamerstellen["teamname"].toString())
                Log.e("ADD_TEAM", "$currentUser")
                messageDialogSuccess(context = context, messageText = "Team gespeichert")
            }
            .addOnFailureListener {
                Log.e("ADD_TEAM", "Error writing document")
            }

    }

    fun updateTeam(context: Context){
        val teamupdate = hashMapOf(
            "pokemon1" to _currentTeam.value.pokemonOne,
            "pokemon2" to _currentTeam.value.pokemonTwo,
            "pokemon3" to _currentTeam.value.pokemonThree,
            "teamname" to _currentTeam.value.teamName,
        )

        firestore.collection("user")
            .document(currentUser?.id.toString())
            .collection("teams")
            .document(_currentTeam.value.teamName)
            .set(teamupdate)
            .addOnSuccessListener {
                Log.e("UPDATE_TEAM", "${currentTeam.value}")
            }
            .addOnFailureListener {
                Log.e("UPDATE_TEAM_FEHLER", "$teamupdate Fehlgeschlagen")
            }
        //deleteCurrentTeam()
        Log.e("TEAM_AFTER_RESET", "${currentTeam.value}")
        getTeam(_currentTeam.value.teamName)
    }

    fun updatePokemonTeam(context: Context, id: Int, pokemonName: String){
        when(id){
            1 -> _currentTeam.value.pokemonOne = pokemonName
            2 -> _currentTeam.value.pokemonTwo = pokemonName
            3 -> _currentTeam.value.pokemonThree = pokemonName
        }
        Log.e("UPDATE_POKEMON_TEAM", "${currentTeam.value}")
        updateTeam(context)
    }

    fun deleteTeam(context: Context, teamId: String){

        currentUser?.teams = currentUser?.teams?.minus(1)!!

        firestore.collection("user")
            .document(currentUser?.id.toString())
            .collection("teams")
            .document(teamId)
            .delete()
            .addOnSuccessListener {
                updateFireStoreUser(context)
                _currentTeam.value = BattleTeams("","","","")
                messageDialogSuccess(context,"Löschen des Teams erfolgreich")
            }
            .addOnFailureListener {
                messageDialogError(context, "Das Team konnte nicht gelöscht werden")
                Log.e("TEAM_LÖSCHEN", "Das Team konnte nicht gelöscht werden")
            }
    }

    fun deletePokemoninTeam(context: Context, pokeID: Int, teamId: String){
        when(pokeID){
            1 -> _currentTeam.value.pokemonOne = ""
            2 -> _currentTeam.value.pokemonTwo = ""
            3 -> _currentTeam.value.pokemonThree = ""
        }
        val teamupdate = hashMapOf(
            "pokemon1" to _currentTeam.value.pokemonOne,
            "pokemon2" to _currentTeam.value.pokemonTwo,
            "pokemon3" to _currentTeam.value.pokemonThree,
            "teamname" to _currentTeam.value.teamName,
        )

        firestore.collection("user")
            .document(currentUser?.id.toString())
            .collection("teams")
            .document(_currentTeam.value.teamName)
            .set(teamupdate)
            .addOnSuccessListener {
                Log.e("UPDATE_TEAM_BEARBEITEN", "${currentTeam.value}")
            }
            .addOnFailureListener {
                Log.e("ADD_TEAM", "Error writing document")
            }
        deleteCurrentTeam()
        getTeam(teamId)
    }

    fun loadOwnedPokemon() {
        firestore.collection("user")
            .document(currentUser?.id.toString())
            .collection("meinepokemon")
            .get()
            .addOnSuccessListener { result ->
                val pokemonList = mutableListOf<PokemonGrindEntry>()
                for (document in result) {
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

    fun deleteCurrentTeam() {
        _currentTeam.value = BattleTeams("", "", "", "")
    }

    fun getTeam(id: String): BattleTeams {
        firestore.collection("user")
            .document(currentUser?.id.toString())
            .collection("teams")
            .document(id)
            .get()
            .addOnSuccessListener { result ->
                _currentTeam.value = BattleTeams(
                    result.data?.get("teamname").toString(),
                    result.data?.get("pokemon1").toString(),
                    result.data?.get("pokemon2").toString(),
                    result.data?.get("pokemon3").toString()
                )
                Log.e("REPOSITORY", "${_currentTeam.value}")
            }
            .addOnFailureListener { exception ->
                Log.d("REPOSITORY", "Fehler beim laden des Teams: ", exception)
            }
        return _currentTeam.value ?: BattleTeams("", "", "", "")
    }

    fun loadAktivTeam() : BattleTeams{
        firestore.collection("user")
            .document(currentUser?.id.toString())
            .collection("teams")
            .document(currentUser?.aktivteam.toString())
            .get()
            .addOnSuccessListener { result ->
                _currentTeam.value = BattleTeams(
                    result.data?.get("teamname").toString(),
                    result.data?.get("pokemon1").toString(),
                    result.data?.get("pokemon2").toString(),
                    result.data?.get("pokemon3").toString()
                )
                Log.e("REPOSITORY", "${_currentTeam.value}")
            }
            .addOnFailureListener { exception ->
                Log.d("REPOSITORY", "Fehler beim laden des Teams: ", exception)
            }
        return _currentTeam.value ?: BattleTeams("", "", "", "")
    }

    fun deleteAktivTeam(context: Context){
        currentUser?.aktivteam = "null"
        deleteCurrentTeam()
        Log.e("DELETE_AKTIV_TEAM", "${currentTeam.value}")
        updateFireStoreUser(context = context)
    }

    fun updateCurrentUser(id: String) {
        Log.e("GIVEN_ID", "$id")
        // Firestore-Dokument mit der angegebenen ID abrufen
        firestore.collection("user").document(id)
            .get()
            .addOnSuccessListener { result ->
                Log.e("DOK_ID", "${result.id}")
                // Benutzerobjekt mit den abgerufenen Daten erstellen
                currentUser = User(
                    result.id,
                    result.data?.get("alias").toString(),
                    result.data?.get("pokedollar").toString().toInt(),
                    result.data?.get("pokemontickets").toString().toInt(),
                    result.data?.get("avatar").toString(),
                    result.data?.get("teams").toString().toInt(),
                    result.data?.get("aktivteam").toString()
                )
                loadAktivTeam()
                Log.e("CUREENT_USER", "$currentUser")
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
            "pokemontickets" to currentUser?.pokemontickets,
            "teams" to currentUser?.teams,
            "aktivteam" to currentUser?.aktivteam
        )
        firestore.collection("user").document(currentUser?.id.toString())
            .set(updatedUser)
            .addOnSuccessListener {
                messageDialogSuccess(context = context, messageText = "Benutzer wurde geupdated")
            }
            .addOnFailureListener { Log.w("USER_UPDATE", "Update des User fehlgeschlagen") }
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