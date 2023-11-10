package com.klimpel.abschlussarbeitmodul3.viewmodels

import android.content.Context
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klimpel.abschlussarbeitmodul3.data.models.Avatar
import com.klimpel.abschlussarbeitmodul3.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfilViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    var avatarList = mutableStateOf<List<Avatar>>(listOf())

    val currentUser = repository.currentUser


    fun updateCurrentUser(id: String) {
        repository.updateCurrentUser(id)
    }

    fun findAvatar(): Avatar? {
        return repository.findAvatar(currentUser?.avatar.toString())
    }

    fun findAvatar2(): Int {
        return repository.findAvatarInt(currentUser?.avatar.toString())
    }

    fun loadListOfAvatar(): List<Avatar> {
        avatarList.value = repository.loadListOfAvatars()
        return avatarList.value
    }

    fun updateFireStoreUser(context: Context) {
        repository.updateFireStoreUser(context)
    }
}