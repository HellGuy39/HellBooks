package com.hellguy39.hellbooks.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = BookEntity.TABLE_NAME)
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val author: String,
    val date: String,
    val genre: String,
    val description: String,
    val previewUrl: String,
    val bookUrl: String,
    val inBookmarks: Boolean,
    val isRead: Boolean,
) {
    companion object {
        const val TABLE_NAME = "book"
    }
}

