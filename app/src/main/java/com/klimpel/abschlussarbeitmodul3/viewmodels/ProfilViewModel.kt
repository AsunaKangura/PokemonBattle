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
    val teamList = repository.teamList

    init {
        loadTeamList()
    }

    fun updateAlias(alias: String, context: Context){
        repository.firebase.updateAlias(alias, context)
    }

    fun updateAvatar(avatarname: String, context: Context){
        repository.firebase.updateAvatar(avatarname, context)
    }






    fun loadTeamList(){
        repository.firebase.loadTeamList()
    }

    fun updateCurrentUser(id: String) {
        repository.firebase.updateCurrentUser(id)
        //repository.updateCurrentUser(id)
    }

    fun findAvatar(): Avatar? {
        return repository.findAvatar(currentUser?.avatar.toString())
    }

    fun findAvatarInt(name: String): Int {
        return repository.findAvatarInt(name)
    }

    fun loadListOfAvatar(): List<Avatar> {
        avatarList.value = repository.loadListOfAvatars()
        return avatarList.value
    }

    fun updateFireStoreUser(context: Context) {
        repository.firebase.updateFireStoreUser(context)
        //repository.updateFireStoreUser(context)
    }
}