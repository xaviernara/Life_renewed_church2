package com.example.life_renewed.repo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.life_renewed.model.AnnouncementsItem
import com.example.life_renewed.repo.dao.LifeRenewDAO

@Database(entities = [AnnouncementsItem::class], version = 1, exportSchema = false)
abstract class LifeRenewDatabase : RoomDatabase() {

    abstract fun lifeRenewDao(): LifeRenewDAO

}