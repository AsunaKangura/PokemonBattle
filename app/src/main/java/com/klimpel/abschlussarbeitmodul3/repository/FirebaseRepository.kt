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
    var loadStoreCategory: StateFlow<MutableList<StroreCategoryItem>> = _loadStoreCategory.asStateFlow()


    /**
     * Registers a new user with the provided email and password.
     *
     * @param context The context in which the registration is being performed.
     * @param email The email address of the user.
     * @param password The password for the user.
     */
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

    /**
     * Loads the list of battle teams for the current user from Firestore.
     */
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

    /**
     * Loads a specific battle team for the current user from Firestore.
     *
     * @param name The name of the team to load.
     * @return The loaded BattleTeams object representing the team.
     */
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

    /**
     * Updates the alias of the current user with the provided value and updates the corresponding user document in Firestore.
     *
     * @param alias The new alias for the user.
     * @param context The context in which the update is being performed.
     */
    fun updateAlias(alias: String, context: Context) {
        currentUser?.alias = alias
        updateFireStoreUser()
    }

    /**
     * Updates the avatar of the current user with the provided avatar name and updates the corresponding user document in Firestore.
     *
     * @param avatarname The name of the new avatar for the user.
     * @param context The context in which the update is being performed.
     */
    fun updateAvatar(avatarname: String, context: Context) {
        currentUser?.avatar = avatarname
        updateFireStoreUser()
    }

    /**
     * Adds a Pokemon to the addTeam value based on the given name and ID.
     *
     * @param name The name of the Pokemon to add.
     * @param id The ID representing the position of the Pokemon in the team.
     */
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

    /**
     * Adds a team name to the addTeam value.
     *
     * @param name The name of the team to add.
     */
    fun teamNameHinzufugenAddTeam(name: String) {
        _addTeam.value.teamName = name
    }

    /**
     * Adds a new team to Firestore with the details from the addTeam value.
     *
     * @param context The context in which the team addition is being performed.
     * @param navController The NavController used for navigation.
     */
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
                updateFireStoreUser()
                deleteAddTeam()
                navController.navigate(Screen.ProfilScreen.route)
                messageDialogSuccess(context, "Team gespeichert")
            }
            .addOnFailureListener {
                messageDialogError(context, "Team erstellen fehlgeschlagen")
            }
    }

    /**
     * Deletes a team from Firestore based on the provided BattleTeams object.
     *
     * @param context The context in which the team deletion is being performed.
     * @param battleTeams The BattleTeams object representing the team to be deleted.
     */
    fun deleteTeam(context: Context, battleTeams: BattleTeams) {

        currentUser?.teams = currentUser?.teams?.minus(1)!!

        firestore.collection("user")
            .document(currentUser?.id.toString())
            .collection("teams")
            .document(battleTeams.teamName)
            .delete()
            .addOnSuccessListener {
                updateFireStoreUser()
                messageDialogSuccess(
                    context,
                    "Das Löschen des \"${battleTeams.teamName}\" Team war erfolgreich"
                )
            }
            .addOnFailureListener {
                messageDialogError(context, "Das Löchen des Teams ist fehlgeschlagen")
            }
    }

    /**
     * Loads the owned Pokemon data from Firestore and populates the ownedPokemonList LiveData.
     */
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

    /**
     * Deletes the addTeam value by resetting it to an empty BattleTeams object.
     */
    private fun deleteAddTeam() {
        _addTeam.value = BattleTeams("", "", "", "")
    }

    /**
     * Deletes the currentTeam value by resetting it to an empty BattleTeams object.
     */
    fun deleteCurrentTeam() {
        _currentTeam.value = BattleTeams("", "", "", "", 0, 0, 0, 0, 0, 0, 0, 0)
    }

    /**
     * Loads the store categories from Firestore and populates the loadStoreCategory LiveData.
     */
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
                }
                _loadStoreCategory.value = sublist
            }
            .addOnFailureListener {
                Log.e("LOAD_STORE_CATEGORY", "Beim Laden der Kategorien ist ein Fehler aufgetreten")
            }
    }

    /**
     * Updates the user data in Firestore with the current user's information.
     * @param context The context used to display a success message dialog.
     */
    private fun updateFireStoreUser() {
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
                Log.w("USER_UPDATE", "Update des User Erfolgreich")
            }
            .addOnFailureListener { Log.e("USER_UPDATE", "Update des User fehlgeschlagen") }
    }

    /**
     * Updates the current user's data by fetching the Firestore document with the specified ID.
     * @param id The ID of the Firestore document corresponding to the user.
     */
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

    /**
     * Loads the active team for the current user from Firestore and populates the _currentTeam LiveData.
     * @return The loaded BattleTeams object representing the active team.
     */
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