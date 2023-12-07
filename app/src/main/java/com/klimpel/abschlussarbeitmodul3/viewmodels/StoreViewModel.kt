package com.klimpel.abschlussarbeitmodul3.viewmodels

import androidx.lifecycle.ViewModel
import com.klimpel.abschlussarbeitmodul3.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {
    // List of store categories from repository
    val categoryList = repository.storecategoryList

    init {
        // Load store categories on initialization
        loadStoreCategory()
    }

    /**
     * Load the store category list from Firebase.
     */
    private fun loadStoreCategory() {
        repository.firebase.loadStoreCategory()
    }
}