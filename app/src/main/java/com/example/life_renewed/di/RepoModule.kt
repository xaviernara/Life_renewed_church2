package com.example.life_renewed.di

import com.example.life_renewed.repo.LifeRenewRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun provideRepo(impl: LifeRenewRepoImpl): LifeRenewRepoImpl

}