package com.example.life_renewed.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.life_renewed.db.dao.LifeRenewDAO
import com.example.life_renewed.db.LifeRenewDatabase
import com.example.life_renewed.db.Migrations
import com.example.life_renewed.db.dao.NotesDAO
import com.example.life_renewed.utils.Utils
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
            name ="life_renew_database"
        ).addMigrations(*Migrations.getMigrations()).build()
    }

    @Provides
    @Singleton
    fun provideDao(db: LifeRenewDatabase): NotesDAO {
        return db.notesDao()
    }

}