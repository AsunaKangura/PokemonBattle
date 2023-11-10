package com.klimpel.abschlussarbeitmodul3.di

import com.klimpel.abschlussarbeitmodul3.data.remote.PokeApi
import com.klimpel.abschlussarbeitmodul3.repository.PokemonRepository
import com.klimpel.abschlussarbeitmodul3.util.Contants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Das AppModule-Objekt, das die Abhängigkeiten für die Anwendung bereitstellt.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /**
     * Stellt das PokemonRepository bereit.
     *
     * @param api Die PokeApi-Abhängigkeit.
     * @return Das PokemonRepository-Objekt.
     */
    @Singleton
    @Provides
    fun providePokemonRepository(
        api: PokeApi
    ) = PokemonRepository(api)

    /**
     * Stellt die PokeApi-Abhängigkeit bereit.
     *
     * @return Die PokeApi-Instanz.
     */
    @Singleton
    @Provides
    fun providePokeApi(): PokeApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PokeApi::class.java)
    }
}