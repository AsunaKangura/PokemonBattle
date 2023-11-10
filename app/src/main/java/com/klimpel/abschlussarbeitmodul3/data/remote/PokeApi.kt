package com.klimpel.abschlussarbeitmodul3.data.remote

import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon
import com.asunakangura.pokemonbattle.data.remote.responses.PokemonList
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Schnittstelle für die PokeApi.
 */
interface PokeApi {
    /**
     * Ruft eine Liste von Pokemon ab.
     *
     * @param limit Die maximale Anzahl von Pokemon in der Liste.
     * @param offset Der Offset, um Pokemon in der Liste zu überspringen.
     * @return Die PokemonList, die die abgerufene Liste von Pokemon enthält.
     */
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonList

    /**
     * Ruft Informationen zu einem bestimmten Pokemon ab.
     *
     * @param name Der Name des Pokemon.
     * @return Das Pokemon-Objekt, das die abgerufenen Informationen enthält.
     */
    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String
    ): Pokemon
}