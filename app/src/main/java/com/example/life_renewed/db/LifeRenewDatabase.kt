package com.example.life_renewed.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.life_renewed.model.AnnouncementsItem
import com.example.life_renewed.db.dao.LifeRenewDAO
import com.example.life_renewed.db.dao.NotesDAO
import com.example.life_renewed.model.NotesObject

@Database(entities = [NotesObject::class], version = 2, exportSchema = false)
abstract class LifeRenewDatabase : RoomDatabase() {

    abstract fun lifeRenewDao(): LifeRenewDAO

    abstract fun notesDao(): NotesDAO

}