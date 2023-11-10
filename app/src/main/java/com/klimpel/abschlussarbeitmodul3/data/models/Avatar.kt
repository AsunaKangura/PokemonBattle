package com.klimpel.abschlussarbeitmodul3.data.models

/**
 * Represents an avatar object.
 *
 * @property name The name of the avatar.
 * @property imageResource The resource ID of the avatar's image.
 * @property isClicked Indicates whether the avatar has been clicked or not.
 */
data class Avatar(
    val name: String,
    val imageResource: Int,
    var isClicked: Boolean = false
)
