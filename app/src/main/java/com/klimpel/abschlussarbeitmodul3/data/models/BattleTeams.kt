package com.klimpel.abschlussarbeitmodul3.data.models

import com.asunakangura.pokemonbattle.data.remote.responses.Pokemon

data class BattleTeams(
    val teamName: String,
    val pokemonOne: String,
    val pokemonTwo: String,
    val pokemonThree: String,
)
