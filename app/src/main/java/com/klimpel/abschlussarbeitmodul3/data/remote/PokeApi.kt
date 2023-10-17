package com.klimpel.abschlussarbeitmodul3.data.remote

import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon
import com.asunakangura.pokemonbattle.data.remote.responses.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Eine Schnittstelle für die PokeApi, die verschiedene Endpunkte zum Abrufen von Pokémon-Daten bereitstellt.
 */
interface PokeApi {
    /**
     * Ruft eine Liste von Pokémon ab, basierend auf einer begrenzten Anzahl und einem Versatz.
     *
     * @param limit Die maximale Anzahl von Pokémon in der Liste.
     * @param offset Der Versatz, um das Abrufen der Pokémon-Liste zu starten.
     * @return Eine PokemonListe mit den abgerufenen Pokémon.
     */
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonList

    /**
     * Ruft Informationen zu einem bestimmten Pokémon basierend auf seinem Namen ab.
     *
     * @param name Der Name des Pokémon, von dem Informationen abgerufen werden sollen.
     * @return Ein Pokémon-Objekt mit den abgerufenen Informationen.
     */
    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String
    ): Pokemon
}