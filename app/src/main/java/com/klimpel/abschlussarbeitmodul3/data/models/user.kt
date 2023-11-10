package com.klimpel.abschlussarbeitmodul3.data.models

/**
 * Represents a user in the system.
 *
 * @property id The unique identifier of the user.
 * @property alias The user's alias or username.
 * @property pokedollar The amount of pokedollars the user has.
 * @property pokemontickets The number of pokemontickets the user has.
 * @property avatar The image URL of the user's avatar.
 * @property teams The total number of teams the user is a part of.
 * @property aktivteam The active team of the user.
 */
data class User(
    var id: String,
    var alias: String,
    var pokedollar: Int,
    var pokemontickets: Int,
    var avatar: String,
    var teams: Int,
    var aktivteam: String
)