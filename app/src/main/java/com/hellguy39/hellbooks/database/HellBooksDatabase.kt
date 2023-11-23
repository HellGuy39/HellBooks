package com.hellguy39.hellbooks.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hellguy39.hellbooks.database.dao.BookDao
import com.hellguy39.hellbooks.database.entity.BookEntity


@Database(
    version = 1,
    entities = [
        BookEntity::class,
    ]
)
abstract class HellBooksDatabase: RoomDatabase() {

    abstract val bookDao: BookDao

    companion object {
        const val NAME = "hell-books-database"
    }
}