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
 * Die Annotation "@Singleton" in der Programmierung bezieht sich auf das Designmuster Singleton. Ein Singleton ist ein Entwurfsmuster, bei dem nur eine einzige Instanz einer Klasse existiert und auf diese Instanz von überall im Code zugegriffen werden kann. Die Annotation "@Singleton" wird in einigen Programmiersprachen und Frameworks verwendet, um anzuzeigen, dass eine Klasse als Singleton implementiert ist. Dadurch wird sichergestellt, dass nur eine einzige Instanz dieser Klasse erstellt wird.
 *
 * Die Annotation "@Provides" wird in einigen Dependency Injection-Frameworks wie Dagger verwendet. Sie wird verwendet, um anzuzeigen, dass eine Methode oder ein Modul eine bestimmte Abhängigkeit bereitstellt. Wenn eine Klasse eine Methode mit der Annotation "@Provides" hat, kann das Framework diese Methode aufrufen, um die angeforderte Abhängigkeit zu erfüllen. Es ermöglicht eine deklarative Konfiguration der Abhängigkeiten in einer Anwendung.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Provide a singleton instance of the PokemonRepository
    @Singleton
    @Provides
    fun providePokemonRepository(
        api: PokeApi
    ) = PokemonRepository(api)

    // Provide a singleton instance of the PokeApi
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