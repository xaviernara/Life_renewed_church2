package com.example.life_renewed.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="bible_table")
data class BibleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "book") val book: String,
    @ColumnInfo(name = "chapter") val chapter: Int,
    @ColumnInfo(name = "verse") val verse: Int,
)
