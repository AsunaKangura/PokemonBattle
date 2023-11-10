package com.klimpel.abschlussarbeitmodul3.repository

import android.content.Context
import android.util.Log
import com.klimpel.abschlussarbeitmodul3.Screen
import com.klimpel.abschlussarbeitmodul3.ui.components.messageDialogError
import com.klimpel.abschlussarbeitmodul3.ui.components.messageDialogSuccess
import com.klimpel.abschlussarbeitmodul3.util.Contants.Companion.auth
import com.klimpel.abschlussarbeitmodul3.util.Contants.Companion.firestore
import com.klimpel.abschlussarbeitmodul3.util.generatePokemonAlias
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FirebaseRepository {

    fun register(context: Context, email: String, password: String){

        if(email.isNotEmpty() && password.isNotEmpty() ) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val registerNewUser = hashMapOf(
                            "aktivteam" to "null",
                            "alias" to generatePokemonAlias(),
                            "avatar" to "default",
                            "pokedollar" to "1000".toInt(),
                            "pokemontickets" to "0".toInt(),
                            "teams" to "0".toInt()
                        )
                        firestore.collection("user")
                            .document(auth.currentUser?.uid.toString())
                            .set(registerNewUser)
                            .addOnSuccessListener {
                                messageDialogSuccess(context, "Registrierung war erfolgreich")
                            }
                            .addOnFailureListener {
                                messageDialogError(context, "Registrierung ist fehlgeschlagen")
                            }
                    } else {
                        messageDialogError(context, "Registrieren fehlgeschlagen")
                    }
                }
        }else{
            messageDialogError(context, "Du musst alle Felder ausfüllen")
        }
    }

}