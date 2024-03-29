package com.klimpel.abschlussarbeitmodul3.util

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Contants {

    companion object {

        const val BASE_URL = "https://pokeapi.co/api/v2/"

        const val LIMITS = 151
        const val OFFSET = 0

        val auth = Firebase.auth
        val firestore = Firebase.firestore
    }

}