package com.klimpel.abschlussarbeitmodul3.repository

import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon
import com.asunakangura.pokemonbattle.data.remote.responses.PokemonList
import com.klimpel.abschlussarbeitmodul3.data.remote.PokeApi
import com.klimpel.abschlussarbeitmodul3.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
) {
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
}