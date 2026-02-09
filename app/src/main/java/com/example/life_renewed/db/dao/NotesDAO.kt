package com.example.life_renewed.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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

    //need to modify this to update the note and description by checking id or some other identifier
//    @Query("UPDATE notes_table SET note = :note WHERE id = :id")
   @Update
    fun updateNotes(notesObject: NotesObject)

}