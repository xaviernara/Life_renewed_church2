package com.example.life_renewed.repo

import com.example.life_renewed.model.AnnouncementsItem
import com.example.life_renewed.model.NotesObject
import kotlinx.coroutines.flow.Flow

interface LifeRenewRepository {

    suspend fun getAnnouncementsFromApi(): List<AnnouncementsItem>

    suspend fun insertAnnouncementInDB(announcement: AnnouncementsItem)

    suspend fun getAnnouncementsFromDB(): Flow<List<AnnouncementsItem>>

    suspend fun getNotesFromDB(): Flow<List<NotesObject>>

    suspend fun insertNotesIntoDB(notesObject: NotesObject)

    suspend fun deleteNotesFromDB(notesObject: NotesObject)

    suspend fun getNotesById(id: Int): Flow<NotesObject>

    suspend fun updateNotesInDB(notesObject: NotesObject)
}