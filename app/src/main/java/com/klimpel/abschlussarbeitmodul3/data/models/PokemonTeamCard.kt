package com.klimpel.abschlussarbeitmodul3.data.models

/**
 * Diese Datenklasse repräsentiert eine Pokemon-Teamkarte.
 *
 * @property teamId Die ID des Teams.
 * @property teamName Der Name des Teams.
 * @property isClicked Gibt an, ob die Teamkarte angeklickt wurde oder nicht. Standardmäßig ist sie nicht angeklickt.
 */
data class PokemonTeamCard(
    val teamId: String,
    val teamName: String,
    var isClicked: Boolean = false
)