package com.klimpel.abschlussarbeitmodul3.data.models

/**
 * Stellt einen Eintrag in der Pokedex-Liste dar.
 *
 * @property pokemonName Der Name des Pokémons.
 * @property imageUrl Die URL des Bildes des Pokémons.
 * @property number Die Nummer des Pokémons im Pokedex.
 * @property owned Gibt an, ob das Pokémon im Besitz ist oder nicht.
 */
data class PokedexListEntry(
    val pokemonName: String,
    val imageUrl: String,
    val number: Int,
    val owned: Boolean = false
)