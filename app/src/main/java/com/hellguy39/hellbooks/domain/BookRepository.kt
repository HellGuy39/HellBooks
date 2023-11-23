package com.hellguy39.hellbooks.domain

import com.hellguy39.hellbooks.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {

    suspend fun insertFakeBooks()

    suspend fun updateBook(book: Book)

    fun getBookByIdFlow(id: Int): Flow<Book?>

    val books: Flow<List<Book>>

    val lastBooks: Flow<List<Book>>

    val bookmarks: Flow<List<Book>>

    suspend fun getBooks(): List<Book>

    fun addBookToLastRead(id: Int)

}