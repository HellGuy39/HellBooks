package com.hellguy39.hellbooks.data.di

import com.hellguy39.hellbooks.data.BooksRepositoryImpl
import com.hellguy39.hellbooks.domain.BookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindBookRepository(
        booksRepositoryImpl: BooksRepositoryImpl
    ): BookRepository

}