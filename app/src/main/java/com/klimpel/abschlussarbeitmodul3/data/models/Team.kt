package com.klimpel.abschlussarbeitmodul3.data.models

import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon

data class Team(
    var teamName: String,
    var pokemonOne: Pokemon,
    var pokemonTwo: Pokemon,
    var pokemonThree: Pokemon,
    val games: Int = 0,
    val wins: Int = 0,
)