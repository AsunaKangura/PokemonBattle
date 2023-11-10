package com.klimpel.abschlussarbeitmodul3.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.klimpel.abschlussarbeitmodul3.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AllgemeinesViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {


    fun register(context: Context, email: String, password: String){
        repository.firebase.register(context, email, password)
    }
}