package com.klimpel.abschlussarbeitmodul3.data.models

data class Avatar(
    val name : String,
    val imageResource: Int,
    var isClicked: Boolean = false
)
