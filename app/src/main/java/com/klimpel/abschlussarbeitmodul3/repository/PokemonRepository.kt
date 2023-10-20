package com.klimpel.abschlussarbeitmodul3.repository

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.res.ResourcesCompat
import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon
import com.asunakangura.pokemonbattle.data.remote.responses.PokemonList
import com.klimpel.abschlussarbeitmodul3.data.models.Avatar
import com.klimpel.abschlussarbeitmodul3.data.models.User
import com.klimpel.abschlussarbeitmodul3.data.remote.PokeApi
import com.klimpel.abschlussarbeitmodul3.util.Resource
import com.klimpel.abschlussarbeitmodul3.util.Contants.Companion.firestore
import com.klimpel.pokemonbattlefinal.R
import dagger.hilt.android.scopes.ActivityScoped
import timber.log.Timber
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
) {
    //LiveData von dem eingeloggten User
    var currentUser by mutableStateOf<User?>(null)


    /**
     * Retrieves a list of Pokemon.
     *
     * @param limit The maximum number of Pokemon to retrieve.
     * @param offset The number of Pokemon to skip before starting to retrieve.
     *
     * @return A [Resource] object containing the result of the API call.
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
     * Retrieves information about a specific Pokemon.
     *
     * @param pokemonName The name of the Pokemon to retrieve information for.
     *
     * @return A [Resource] object containing the result of the API call.
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
     * Funktion zum Aktualisieren eines Benutzers
     *
     * @param dokumententID von Firebase Firestore basierend auf den eingelogten User
     */
    fun updateUser(id: String) {
        // Firestore-Dokument mit der angegebenen ID abrufen
        firestore.collection("user").document(id)
            .get()
            .addOnSuccessListener { result ->
                // Benutzerobjekt mit den abgerufenen Daten erstellen
                currentUser = User(
                    result.id,
                    result.data?.get("alias").toString(),
                    result.data?.get("pokedollar").toString().toInt(),
                    result.data?.get("avatar").toString()
                )
            }
            .addOnFailureListener {
                // Fehlerbehandlung bei Fehlschlag des Abrufs
                Log.e("LOADUSER_FEHLER", "$it")
            }
    }


    /** Die Funktion `findAvatar` sucht nach einem Avatar-Objekt in einer Liste von Avataren,
    basierend auf dem Namen des Avatars. Wenn der Avatar gefunden wird, wird er zurückgegeben,
    andernfalls wird `null` zurückgegeben.
    */
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

    fun loadListOfAvatars() : List<String>{
        return listOf ("evoli", "rattfratz", "abra", "menki", "pummeluff", "bisasam", "knofensa", "ponita","enton", "marill", "mauzi", "schiggy", "taubsi","pikachu"  )
    }

}