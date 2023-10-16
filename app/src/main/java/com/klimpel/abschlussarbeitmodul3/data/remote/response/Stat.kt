package com.asunakangura.pokemonbattle.data.remote.responses

import com.google.gson.annotations.SerializedName

data class Stat(
    @SerializedName("base_stat")
    val basestat: Int,
    val effort: Int,
    val stat: StatX
)