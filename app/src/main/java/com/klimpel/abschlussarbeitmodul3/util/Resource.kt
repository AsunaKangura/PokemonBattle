package com.klimpel.abschlussarbeitmodul3.util

/**
 * A sealed class representing a resource that can be in one of three states: Success, Error, or Loading.
 *
 * @param T The type of data associated with the resource.
 * @property data The data associated with the resource.
 * @property message The error message associated with the resource.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null){

    /**
     * Represents a successful resource state.
     *
     * @param data The data associated with the resource.
     */
    class Success<T>(data: T) : Resource<T>(data)

    /**
     * Represents an error resource state.
     *
     * @param message The error message associated with the resource.
     * @param data The data associated with the resource.
     */
    class Error<T>(message: String, data: T? = null ) : Resource<T>(data, message)

    /**
     * Represents a loading resource state.
     *
     * @param data The data associated with the resource.
     */
    class Loading<T>(data: T? = null) : Resource<T>(data)
}