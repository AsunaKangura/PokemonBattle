package com.klimpel.abschlussarbeitmodul3.data.models

data class User(
    var id : String,
    var alias : String,
    var pokedollar: Int,
    var pokemontickets: Int,
    var avatar: String,
    var teams: Int,
    var aktivteam: String
)
