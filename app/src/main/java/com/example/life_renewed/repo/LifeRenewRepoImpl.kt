package com.example.life_renewed.repo

import com.example.life_renewed.network.LifeRenewApi
import com.example.life_renewed.model.AnnouncementsItem
import com.example.life_renewed.db.dao.LifeRenewDAO
import com.example.life_renewed.db.dao.NotesDAO
import com.example.life_renewed.model.NotesObject
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LifeRenewRepoImpl @Inject constructor(
//    private val lifeRenewApi: LifeRenewApi,
//    private val lifeRenewDao: LifeRenewDAO,
    private val notesDao: NotesDAO
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

    override suspend fun getNotesFromDB(): Flow<List<NotesObject>> {
        return notesDao.getAllNotes()
    }

    override suspend fun insertNotesIntoDB(notesObject: NotesObject) {
        notesDao.insertNotes(notesObject)
    }

    override suspend fun deleteNotesFromDB(notesObject: NotesObject) {
        notesDao.deleteNotes(notesObject)
    }

    override suspend fun getNotesById(id: Int): Flow<NotesObject> {
        return notesDao.getNotesById(id)
    }

}