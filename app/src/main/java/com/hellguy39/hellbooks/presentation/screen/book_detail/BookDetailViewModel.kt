package com.hellguy39.hellbooks.presentation.screen.book_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellguy39.hellbooks.domain.BookRepository
import com.hellguy39.hellbooks.model.Book
import com.hellguy39.hellbooks.presentation.navigation.Arguments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel
@Inject
constructor(
    private val bookRepository: BookRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val bookId = savedStateHandle.get<Int>(Arguments.Id.key) ?: Arguments.Id.empty

    val uiState = bookRepository.getBookByIdFlow(bookId)
        .map { book ->
            BookDetailUiState(
                book = book ?: Book.emptyInstance()
            )
        }
        .stateIn(
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = BookDetailUiState(),
            scope = viewModelScope
        )

    fun startReadingBook() {
        viewModelScope.launch {
            bookRepository.addBookToLastRead(bookId)
        }
    }

    fun toggleBookmark() {
        viewModelScope.launch {
            val book = uiState.value.book
            val inBookmarks = book.inBookmarks.not()
            bookRepository.updateBook(book = book.copy(inBookmarks = inBookmarks))
        }
    }

    fun toggleMarkAsRead() {
        viewModelScope.launch {
            val book = uiState.value.book
            val isRead = book.isRead.not()
            bookRepository.updateBook(book = book.copy(isRead = isRead))
        }
    }
}

data class BookDetailUiState(
    val book: Book = Book.emptyInstance()
)