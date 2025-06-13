package com.example.life_renewed.di

import android.content.Context
import androidx.room.Room
import com.example.life_renewed.repo.dao.LifeRenewDAO
import com.example.life_renewed.repo.db.LifeRenewDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LifeRenewDatabase {
        return Room.databaseBuilder(
            context,
            LifeRenewDatabase::class.java,
            "life_renew_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(db: LifeRenewDatabase): LifeRenewDAO {
        return db.lifeRenewDao()
    }


}