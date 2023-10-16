package com.asunakangura.pokemonbattle.data.remote.responses

import com.google.gson.annotations.SerializedName

data class HeldItem(
    val item: Item,
    @SerializedName("version_details")
    val versiondetails: List<VersionDetail>
)