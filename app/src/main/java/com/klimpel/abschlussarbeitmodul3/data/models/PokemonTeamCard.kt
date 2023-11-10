package com.klimpel.abschlussarbeitmodul3.data.models

data class PokemonTeamCard(
    val teamId: String,
    val teamName: String,
    var isClicked: Boolean = false
)