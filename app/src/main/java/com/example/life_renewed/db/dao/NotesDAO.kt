package com.example.life_renewed.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.life_renewed.model.NotesObject
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDAO {

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): Flow<List<NotesObject>>

    @Insert
    fun insertNotes(notesObject: NotesObject)

    @Delete
    fun deleteNotes(notesObject: NotesObject)

    @Query("SELECT * FROM notes_table WHERE id = :id")
    fun getNotesById(id: Int): Flow<NotesObject>
}