package com.klimpel.abschlussarbeitmodul3.viewmodels

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.klimpel.abschlussarbeitmodul3.data.models.Avatar
import com.klimpel.abschlussarbeitmodul3.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfilViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {
    // State for avatar list
    var avatarList = mutableStateOf<List<Avatar>>(listOf())

    // Current user and team list from repository
    val currentUser = repository.currentUser
    val teamList = repository.teamList

    init {
        // Load team list on initialization
        loadTeamList()
    }

    /**
     * Update the alias of the user.
     * @param alias The new alias.
     * @param context The context.
     */
    fun updateAlias(alias: String, context: Context) {
        repository.firebase.updateAlias(alias, context)
    }

    /**
     * Update the avatar of the user.
     * @param avatarName The name of the new avatar.
     * @param context The context.
     */
    fun updateAvatar(avatarName: String, context: Context) {
        repository.firebase.updateAvatar(avatarName, context)
    }

    /**
     * Load the team list from Firebase.
     */
    private fun loadTeamList() {
        repository.firebase.loadTeamList()
    }

    /**
     * Update the current user with the given ID.
     * @param id The ID of the current user.
     */
    fun updateCurrentUser(id: String) {
        repository.firebase.updateCurrentUser(id)
    }

    /**
     * Find the integer representation of the avatar with the given name.
     * @param name The name of the avatar.
     * @return The integer representation of the avatar.
     */
    fun findAvatarInt(name: String): Int {
        return repository.findAvatarInt(name)
    }

    /**
     * Load the list of avatars from the repository.
     * @return The list of avatars.
     */
    fun loadListOfAvatars(): List<Avatar> {
        avatarList.value = repository.loadListOfAvatars()
        return avatarList.value
    }
}