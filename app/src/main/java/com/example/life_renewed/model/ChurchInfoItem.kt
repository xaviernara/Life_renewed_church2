package com.example.life_renewed.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bulletin_items")
data class ChurchInfoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int, // Unique identifier
    val title: String, // Title of the bulletin items,
    val shortDescription: String? = null, // Optional short description,
    val longDescription: List<String>? = null, // An list of strings for long descriptions,
    val imageUrl: String? = null, // Optional image URL,
)
