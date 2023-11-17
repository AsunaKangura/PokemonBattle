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
    var pokemonThree: String,
    val games: Int = 0,
    val wins: Int = 0,
    var hp: Int = 0,
    var atk: Int = 0,
    var def: Int = 0,
    var spatk: Int =0,
    var spdef: Int = 0,
    var spd: Int = 0
)
