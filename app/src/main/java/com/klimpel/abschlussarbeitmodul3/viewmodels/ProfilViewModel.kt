package com.klimpel.abschlussarbeitmodul3.viewmodels

import androidx.lifecycle.ViewModel
import com.klimpel.abschlussarbeitmodul3.data.models.Avatar
import com.klimpel.abschlussarbeitmodul3.data.models.User
import com.klimpel.abschlussarbeitmodul3.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfilViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    val currentUser = repository.currentUser

    fun updateUser(id: String){
        repository.updateUser(id)
    }

    fun findAvatar() : Avatar?{
       return repository.findAvatar(currentUser?.avatar.toString())
    }

    fun loadListOfAvatars() : List<String>{
        return repository.loadListOfAvatars()
    }

}