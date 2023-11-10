package com.klimpel.abschlussarbeitmodul3.data.models

/**
 * Diese Datenklasse repräsentiert einen Eintrag für das Trainieren von Pokemon.
 *
 * @property name Der Name des Pokemon.
 * @property anzahl Die Anzahl der Pokemon, die trainiert werden.
 * @property exp Die Erfahrungspunkte, die das Pokemon erhält.
 * @property level Das Level des Pokemon, nachdem es trainiert wurde.
 * @property id Die ID des Pokemon.
 */
data class PokemonGrindEntry(
    val name: String,
    val anzahl: Int,
    val exp: Int,
    val level: Int,
    val id: Int
)