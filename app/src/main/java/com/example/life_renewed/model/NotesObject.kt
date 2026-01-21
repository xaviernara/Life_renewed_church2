package com.example.life_renewed.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class NotesObject @RequiresApi(Build.VERSION_CODES.O) constructor(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,

    @ColumnInfo(name = "sermonTitle")
    var sermonTitle : String? = null,

    @ColumnInfo(name = "description")
    var description : String? = null,

    @ColumnInfo(name = "seriesTitle")
    var seriesTitle : String? = null,

    @ColumnInfo(name = "date")
    var date : String? = null,

    @ColumnInfo(name = "timeStamp")
    var timeStamp : String? = null
)