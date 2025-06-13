package com.example.life_renewed.di

import com.example.life_renewed.api.LifeRenewApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.example.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesLifeRenewApi(retrofit: Retrofit): LifeRenewApi {
        return retrofit.create(LifeRenewApi::class.java)
    }



}