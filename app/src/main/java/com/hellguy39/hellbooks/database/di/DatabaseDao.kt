package com.hellguy39.hellbooks.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hellguy39.hellbooks.database.HellBooksDatabase
import com.hellguy39.hellbooks.database.dao.BookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): HellBooksDatabase {
        return Room.databaseBuilder(
            context,
            HellBooksDatabase::class.java,
            HellBooksDatabase.NAME
        )
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .addMigrations()
            .build()
    }

    @Provides
    fun provideBookDao(database: HellBooksDatabase): BookDao {
        return database.bookDao
    }

}