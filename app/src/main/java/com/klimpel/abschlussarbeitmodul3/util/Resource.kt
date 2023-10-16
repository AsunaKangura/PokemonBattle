package com.klimpel.abschlussarbeitmodul3.util

/**
 * Diese Zeile definiert eine abstrakte Klasse namens "Resource" mit einem generischen Typen "T". Die Klasse hat zwei Eigenschaften: "data", die den generischen Typen T oder null enthält, und "message", die einen String oder null enthält.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null){

    /**
     * Diese Zeile definiert eine Unterklasse namens "Success", die von der abstrakten Klasse "Resource" erbt. Sie akzeptiert ein Objekt vom generischen Typen "T" und setzt es als "data" in der übergeordneten Klasse.
     */
    class Success<T>(data: T) : Resource<T>(data)

    /**
     * Diese Zeile definiert eine weitere Unterklasse namens "Error", die von der abstrakten Klasse "Resource" erbt. Sie akzeptiert eine Fehlermeldung als String und optional ein Objekt vom generischen Typen "T" als Daten. Diese Werte werden an die übergeordnete Klasse übergeben.
     */
    class Error<T>(message: String, data: T? = null ) : Resource<T>(data, message)

    /**
     * Diese Zeile definiert eine weitere Unterklasse namens "Loading", die von der abstrakten Klasse "Resource" erbt. Sie akzeptiert ebenfalls ein Objekt vom generischen Typen "T" als Daten und setzt es in der übergeordneten Klasse
     */
    class Loading<T>(data: T? = null) : Resource<T>(data)
}