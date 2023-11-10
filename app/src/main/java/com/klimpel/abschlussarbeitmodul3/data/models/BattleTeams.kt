package com.klimpel.abschlussarbeitmodul3.data.models

/**
 * Stellt ein BattleTeams-Objekt dar.
 *
 * @property teamName Der Name des Teams.
 * @property pokemonOne Der Name des ersten Pokémons im Team.
 * @property pokemonTwo Der Name des zweiten Pokémons im Team.
 * @property pokemonThree Der Name des dritten Pokémons im Team.
 */
data class BattleTeams(
    var teamName: String,
    var pokemonOne: String,
    var pokemonTwo: String,
    var pokemonThree: String
)
