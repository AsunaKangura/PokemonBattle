package com.klimpel.abschlussarbeitmodul3.repository

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.data.models.BattleTeams
import com.klimpel.abschlussarbeitmodul3.data.models.PokemonGrindEntry
import com.klimpel.abschlussarbeitmodul3.data.models.StoreItem
import com.klimpel.abschlussarbeitmodul3.data.models.StroreCategoryItem
import com.klimpel.abschlussarbeitmodul3.data.models.User
import com.klimpel.abschlussarbeitmodul3.ui.components.messageDialogError
import com.klimpel.abschlussarbeitmodul3.ui.components.messageDialogSuccess
import com.klimpel.abschlussarbeitmodul3.util.Contants.Companion.auth
import com.klimpel.abschlussarbeitmodul3.util.Contants.Companion.firestore
import com.klimpel.abschlussarbeitmodul3.util.generatePokemonAlias
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FirebaseRepository {

    //LiveData von dem eingeloggten User
    var currentUser by mutableStateOf<User?>(null)

    // Current Team Flow
    private val _currentTeam = MutableStateFlow(BattleTeams("", "", "", ""))
    var currentTeam: StateFlow<BattleTeams> = _currentTeam.asStateFlow()

    private val _addTeam = MutableStateFlow(BattleTeams("", "", "", ""))
    var addteam: StateFlow<BattleTeams> = _addTeam.asStateFlow()

    private var pokemonList by mutableStateOf<MutableList<PokemonGrindEntry>>(mutableListOf())
    private var _ownedPokemonList = MutableStateFlow(pokemonList)
    var ownedPokemonList: StateFlow<List<PokemonGrindEntry>> = _ownedPokemonList.asStateFlow()

    private var currentTeamList by mutableStateOf<MutableList<BattleTeams>>(mutableListOf())
    private var _teamList = MutableStateFlow(currentTeamList)
    var teamList: StateFlow<List<BattleTeams>> = _teamList.asStateFlow()

    private var storeCategoryList by mutableStateOf<MutableList<StroreCategoryItem>>(mutableListOf())
    private var _loadStoreCategory = MutableStateFlow(storeCategoryList)
    var loadStoreCategory: StateFlow<MutableList<StroreCategoryItem>> =
        _loadStoreCategory.asStateFlow()


    fun register(context: Context, email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val registerNewUser = hashMapOf(
                            "aktivteam" to "null",
                            "alias" to generatePokemonAlias(),
                            "avatar" to "default",
                            "pokedollar" to "1000".toInt(),
                            "pokemontickets" to "0".toInt(),
                            "teams" to "0".toInt()
                        )
                        firestore.collection("user")
                            .document(auth.currentUser?.uid.toString())
                            .set(registerNewUser)
                            .addOnSuccessListener {
                                messageDialogSuccess(context, "Registrierung war erfolgreich")
                            }
                            .addOnFailureListener {
                                messageDialogError(context, "Registrierung ist fehlgeschlagen")
                            }
                    } else {
                        messageDialogError(context, "Registrieren fehlgeschlagen")
                    }
                }
        } else {
            messageDialogError(context, "Du musst alle Felder ausfüllen")
        }
    }

    fun loadTeamList() {
        firestore.collection("user")
            .document(currentUser?.id.toString())
            .collection("teams")
            .get()
            .addOnSuccessListener { result ->
                val teamlist = mutableListOf<BattleTeams>()
                for (document in result) {
                    teamlist.add(
                        BattleTeams(
                            document.data.get("teamname").toString(),
                            document.data.get("pokemon1").toString(),
                            document.data.get("pokemon2").toString(),
                            document.data.get("pokemon3").toString(),
                        )
                    )
                }
                _teamList.value = teamlist
                Log.e("TEAMLIST", "$teamlist")
            }
            .addOnFailureListener {
                Log.e("LOAD_TEAM_LIST", "Error getting documents: ")
            }
    }

    fun loadTeam(name: String): BattleTeams {
        firestore.collection("user")
            .document(currentUser?.id.toString())
            .collection("teams")
            .document(name)
            .get()
            .addOnSuccessListener { result ->
                _currentTeam.value = BattleTeams(
                    result.data?.get("teamname").toString(),
                    result.data?.get("pokemon1").toString(),
                    result.data?.get("pokemon2").toString(),
                    result.data?.get("pokemon3").toString(),
                    result.data?.get("games").toString().toInt(),
                    result.data?.get("wins").toString().toInt()
                )
            }
            .addOnFailureListener {

            }
        return _currentTeam.value ?: BattleTeams("", "", "", "")
    }

    fun updateAlias(alias: String, context: Context) {
        currentUser?.alias = alias
        updateFireStoreUser(context)
    }

    fun updateAvatar(avatarname: String, context: Context) {
        currentUser?.avatar = avatarname
        updateFireStoreUser(context)
    }

    fun pokemonHinzufugenAddTeam(name: String, id: Int) {
        when (id) {
            1 -> {
                _addTeam.value.pokemonOne = name
            }

            2 -> {
                _addTeam.value.pokemonTwo = name
            }

            3 -> {
                _addTeam.value.pokemonThree = name
            }
        }
    }

    fun teamNameHinzufugenAddTeam(name: String) {
        _addTeam.value.teamName = name
    }

    fun teamHinzufugen(context: Context, navController: NavController) {
        val newteam = hashMapOf(
            "teamname" to _addTeam.value.teamName,
            "pokemon1" to _addTeam.value.pokemonOne,
            "pokemon2" to _addTeam.value.pokemonTwo,
            "pokemon3" to _addTeam.value.pokemonThree,
            "games" to _addTeam.value.games,
            "wins" to _addTeam.value.wins
        )

        currentUser?.teams = currentUser?.teams?.plus(1)!!

        firestore.collection("user")
            .document(currentUser?.id.toString())
            .collection("teams")
            .document(addteam.value.teamName)
            .set(newteam)
            .addOnSuccessListener {
                updateFireStoreUser(context)
                deleteAddTeam()
                navController.navigate(Screen.ProfilScreen.route)
                messageDialogSuccess(context, "Team gespeichert")
            }
            .addOnFailureListener {
                messageDialogError(context, "Team erstellen fehlgeschlagen")
            }
    }

    fun deleteTeam(context: Context, battleTeams: BattleTeams) {

        currentUser?.teams = currentUser?.teams?.minus(1)!!

        firestore.collection("user")
            .document(currentUser?.id.toString())
            .collection("teams")
            .document(battleTeams.teamName)
            .delete()
            .addOnSuccessListener {
                updateFireStoreUser(context)
                messageDialogSuccess(
                    context,
                    "Das Löschen des \"${battleTeams.teamName}\" Team war erfolgreich"
                )
            }
            .addOnFailureListener {
                messageDialogError(context, "Das Löchen des Teams ist fehlgeschlagen")
            }
    }

    fun loadOwnedPokemon() {
        firestore.collection("user")
            .document(currentUser?.id.toString())
            .collection("meinepokemon")
            .get()
            .addOnSuccessListener { result ->
                val pokemonList = mutableListOf<PokemonGrindEntry>()

                for (document in result) {
                    //val typeMap = (document.data["type"] as Map<String, *>).toList()
                    pokemonList.add(
                        PokemonGrindEntry(
                            document.data["name"].toString(),
                            document.data["anzahl"].toString().toInt(),
                            document.data["collectedexp"].toString().toInt(),
                            document.data["level"].toString().toInt(),
                            document.data["id"].toString().toInt(),
                            document.data["type1"].toString(),
                            document.data["type2"].toString()
                        )
                    )
                }
                _ownedPokemonList.value = pokemonList
            }
            .addOnFailureListener {
                Log.e("LOAD_OWNED_POKEMON", "Lader der eigenen Pokemon fehlgeschlagen")
            }
    }

    private fun deleteAddTeam() {
        _addTeam.value = BattleTeams("", "", "", "")
    }

    fun deleteCurrentTeam() {
        _currentTeam.value = BattleTeams("", "", "", "", 0, 0, 0, 0, 0, 0, 0, 0)
    }

    fun loadStoreCategory() {
        firestore.collection("store")
            .get()
            .addOnSuccessListener { result ->
                var sublist = mutableListOf<StroreCategoryItem>()
                for (document in result) {
                    sublist.add(
                        StroreCategoryItem(
                            id = document.id,
                            name = document.data["name"].toString()
                        )
                    )
                    //loadStoreItem(document.id)
                }
                _loadStoreCategory.value = sublist
            }
            .addOnFailureListener {
                Log.e("LOAD_STORE_CATEGORY", "Beim Laden der Kategorien ist ein Fehler aufgetreten")
            }
    }

    fun loadStoreItem(category: String){
        firestore.collection("store")
            .document(category)
            .collection(category)
            .get()
            .addOnSuccessListener { result ->
                Log.e("RESULT", "${result.documents}")
                Log.e("RESULT_Cat", "${category}")
                val subMap = mutableMapOf<String, MutableList<StoreItem>>()
                for (document in result) {
                    subMap.set(
                        key = document.id,
                        value = mutableListOf(
                            StoreItem(
                                name = document.data["name"].toString(),
                                beschreibung = document.data["Beschreibung"].toString(),
                                price1 = document.data["price1"].toString().toInt(),
                                price2 = document.data["price2"].toString().toInt(),
                                price3 = document.data["price3"].toString().toInt(),
                                price4 = document.data["price4"].toString().toInt(),
                            )
                        )
                    )
                }
            }
            .addOnFailureListener {
                Log.e("RESULT_FEHLER", "$it")
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

    fun updateCurrentUser(id: String) {
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
            }
            .addOnFailureListener {
                // Fehlerbehandlung bei Fehlschlag des Abrufs
                Log.e("LOADUSER_FEHLER", "$it")
            }
    }

    private fun loadAktivTeam(): BattleTeams {
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
            }
            .addOnFailureListener { exception ->
                Log.d("REPOSITORY", "Fehler beim laden des Teams: ", exception)
            }
        return _currentTeam.value ?: BattleTeams("", "", "", "")
    }


}