package com.example.life_renewed.repo.dao

import androidx.core.location.LocationRequestCompat.Quality
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.life_renewed.model.AnnouncementsItem
import com.example.life_renewed.model.BulletinItem

@Dao
interface LifeRenewDAO {

    @Insert
    fun insert(bulletinItem: BulletinItem)

    @Query("SELECT * FROM bulletin_items")
    fun getAll(): List<BulletinItem>

    @Query("SELECT * FROM bulletin_items WHERE id = :id")
    fun getBulletinItem(id: Int): List<BulletinItem>



    @Query("SELECT * FROM announcements_table WHERE wedding_anniversaries = :weddingAnniversary")
    fun getAnnouncements(weddingAnniversary: String): List<AnnouncementsItem>

    @Insert
    fun insertAnnouncement(vararg announcementsItem: AnnouncementsItem)

    @Query("DELETE FROM announcements_table")
    fun deleteAnnouncements()







}