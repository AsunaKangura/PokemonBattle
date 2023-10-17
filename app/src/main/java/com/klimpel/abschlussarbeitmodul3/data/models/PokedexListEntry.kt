package com.klimpel.abschlussarbeitmodul3.data.models


// Datenklasse für einen Eintrag in der Pokedex-Liste
data class PokedexListEntry(
    // Name des Pokemons
    val pokemonName: String,
    // URL des Bildes des Pokemons
    val imageUrl: String,
    // Nummer des Pokemons
    val number: Int,
    // Gibt an, ob das Pokemon im Besitz ist (Standardwert: false)
    val owned: Boolean = false
)