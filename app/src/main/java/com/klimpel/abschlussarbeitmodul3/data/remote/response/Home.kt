package com.asunakangura.pokemonbattle.data.remote.responses

import com.google.gson.annotations.SerializedName

data class Home(
    @SerializedName("front_default")
    val frontdefault: String,
    @SerializedName("front_female")
    val frontfemale: Any,
    @SerializedName("front_shiny")
    val frontshiny: String,
    @SerializedName("front_shiny_female")
    val frontshinyfemale: Any
)