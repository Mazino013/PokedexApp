package com.example.pokedexapp.di

import android.content.Context
import com.example.pokedexapp.data.remote.api.PokemonApi
import com.example.pokedexapp.data.remote.cache.PokemonCache
import com.example.pokedexapp.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/*Repository DI*/
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApi, cache: PokemonCache): PokemonRepository {
        return PokemonRepository(api, cache)
    }
}
/*Api DI*/
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providePokemonApi(): PokemonApi {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }
}
/*Cache DI*/
@Module
@InstallIn(SingletonComponent::class)
object CacheModule {
    @Provides
    @Singleton
    fun providePokemonCache(@ApplicationContext context: Context): PokemonCache {
        return PokemonCache(context)
    }
}