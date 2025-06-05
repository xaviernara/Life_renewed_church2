package com.example.life_renewed.repo

import com.example.life_renewed.api.LifeRenewApi
import com.example.life_renewed.model.AnnouncementsItem
import com.example.life_renewed.repo.dao.LifeRenewDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LifeRenewRepoImpl @Inject constructor(
    private val lifeRenewApi: LifeRenewApi,
    private val lifeRenewDao: LifeRenewDAO
) : LifeRenewRepository {
    override suspend fun getAnnouncementsFromApi(): List<AnnouncementsItem> {
        TODO("Not yet implemented")
    }

    override suspend fun insertAnnouncementInDB(announcement: AnnouncementsItem) {
        TODO("Not yet implemented")
    }

    override suspend fun getAnnouncementsFromDB(): Flow<List<AnnouncementsItem>> {
        TODO("Not yet implemented")
    }

}