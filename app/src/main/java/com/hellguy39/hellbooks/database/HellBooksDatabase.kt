package com.hellguy39.hellbooks.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hellguy39.hellbooks.database.dao.BookDao
import com.hellguy39.hellbooks.database.dao.UserDao
import com.hellguy39.hellbooks.database.entity.BookEntity
import com.hellguy39.hellbooks.database.entity.UserEntity


@Database(
    version = 1,
    entities = [
        BookEntity::class,
        UserEntity::class,
    ]
)
abstract class HellBooksDatabase: RoomDatabase() {

    abstract val bookDao: BookDao

    abstract val userDao: UserDao

    companion object {
        const val NAME = "hell-books-database"
    }
}