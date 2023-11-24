package com.hellguy39.hellbooks.data.repository

import com.hellguy39.hellbooks.data.fake.fakeBooks
import com.hellguy39.hellbooks.data.mapper.toBook
import com.hellguy39.hellbooks.data.mapper.toBookEntity
import com.hellguy39.hellbooks.database.dao.BookDao
import com.hellguy39.hellbooks.domain.BookRepository
import com.hellguy39.hellbooks.model.Book
import com.hellguy39.hellbooks.prefs.PrefsStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BooksRepositoryImpl
@Inject
constructor(
    private val bookDao: BookDao,
    private val prefsStorage: PrefsStorage
): BookRepository {

    override val books: Flow<List<Book>> = bookDao.getAllFlow()
        .map { list -> list.map { bookEntity -> bookEntity.toBook() } }

    override val lastBooks: Flow<List<Book>> = combine(
        books, prefsStorage.lastReadQueueFlow,
    ) { books, lastReadQueue ->
        lastReadQueue.mapNotNull { id ->
            books.find { book -> book.id == id }
        }
    }

    override val bookmarks: Flow<List<Book>> = books
        .map { list -> list.filter { book -> book.inBookmarks } }

    override fun addBookToLastRead(id: Int) {
        prefsStorage.addToLastReadQueue(id)
    }

    override fun getBookByIdFlow(id: Int): Flow<Book?> {
        return bookDao.findByIdFlow(id)
            .map { it?.toBook() }
    }

    override suspend fun getBooks(): List<Book> {
        return bookDao.getAll().map { it.toBook() }
    }

    override suspend fun insertFakeBooks() {
        fakeBooks.forEach { book ->
            bookDao.insert(book.copy(id = 0).toBookEntity())
        }
    }

    override suspend fun updateBook(book: Book) {
        bookDao.update(book.toBookEntity())
    }

}