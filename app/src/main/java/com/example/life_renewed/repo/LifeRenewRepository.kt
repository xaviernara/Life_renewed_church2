package com.example.life_renewed.repo

import com.example.life_renewed.model.AnnouncementsItem
import kotlinx.coroutines.flow.Flow

interface LifeRenewRepository {

    suspend fun getAnnouncementsFromApi(): List<AnnouncementsItem>

    suspend fun insertAnnouncementInDB(announcement: AnnouncementsItem)

    suspend fun getAnnouncementsFromDB(): Flow<List<AnnouncementsItem>>
}