package com.example.life_renewed.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "announcements_table")
data class AnnouncementsItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "date")
    var date: String?,

    @ColumnInfo(name = "wedding_anniversaries")
    var weddingAnniversaries: List<String>?,

    @ColumnInfo(name = "devotional")
    var devotional: String?,

    @ColumnInfo(name = "birthdays")
    var birthdays : List<String>?,

    @ColumnInfo(name = "prayers")
    var prayers : List<String>?,

    @ColumnInfo(name = "church_profile_info")
    var churchProfileInfo : String?,

    @ColumnInfo(name = "media_team_description")
    var mediaTeamDescription : String?,









)
