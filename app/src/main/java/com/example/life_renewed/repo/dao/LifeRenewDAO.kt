package com.example.life_renewed.repo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.life_renewed.model.AnnouncementsItem

@Dao
interface LifeRenewDAO {

//    @Insert
//    fun insert(bulletinItem: BulletinItem)
//
//    @Query("SELECT * FROM bulletin_items")
//    fun getAll(): List<BulletinItem>
//
//    @Query("SELECT * FROM bulletin_items WHERE id = :id")
//    fun getBulletinItem(id: Int): List<BulletinItem>



    @Query("SELECT * FROM announcements_table ")
    fun getAnnouncements(): List<AnnouncementsItem>

    @Insert
    fun insertAnnouncement(vararg announcementsItem: AnnouncementsItem)

    @Query("DELETE FROM announcements_table")
    fun deleteAnnouncements()







}