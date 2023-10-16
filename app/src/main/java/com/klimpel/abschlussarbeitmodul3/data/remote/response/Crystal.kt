package com.asunakangura.pokemonbattle.data.remote.responses

import com.google.gson.annotations.SerializedName

data class Crystal(
    @SerializedName("back_default")
    val backdefault: String,
    @SerializedName("back_shiny")
    val backshiny: String,
    @SerializedName("back_shiny_transparent")
    val backshinytransparent: String,
    @SerializedName("back_transparent")
    val backtransparent: String,
    @SerializedName("front_default")
    val frontdefault: String,
    @SerializedName("front_shiny")
    val frontshiny: String,
    @SerializedName("front_shiny_transparent")
    val frontshinytransparent: String,
    @SerializedName("front_transparent")
    val fronttransparent: String
)