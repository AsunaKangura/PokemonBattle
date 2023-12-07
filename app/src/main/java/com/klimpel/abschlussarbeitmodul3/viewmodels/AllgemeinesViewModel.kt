package com.klimpel.abschlussarbeitmodul3.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.klimpel.abschlussarbeitmodul3.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel class for handling general operations.
 *
 * @param repository The PokemonRepository instance.
 */
@HiltViewModel
class AllgemeinesViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {
    /**
     * Registers a user using the Firebase authentication.
     *
     * @param context The context.
     * @param email The email of the user.
     * @param password The password of the user.
     */
    fun register(context: Context, email: String, password: String) {
        repository.firebase.register(context, email, password)
    }
}