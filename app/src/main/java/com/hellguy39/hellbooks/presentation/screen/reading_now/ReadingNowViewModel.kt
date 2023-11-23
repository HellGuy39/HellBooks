package com.hellguy39.hellbooks.presentation.screen.reading_now

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellguy39.hellbooks.domain.BookRepository
import com.hellguy39.hellbooks.model.Book
import com.hellguy39.hellbooks.presentation.screen.library.LibraryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReadingNowViewModel
@Inject
constructor(
    private val bookRepository: BookRepository
): ViewModel() {

    val uiState = bookRepository.lastBooks
        .map { lastBooks ->
            ReadingNowUiState(
                lastBooks = lastBooks
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ReadingNowUiState()
        )

    fun toggleBookmark(book: Book) {
        viewModelScope.launch {
            val inBookmarks = book.inBookmarks.not()
            bookRepository.updateBook(book = book.copy(inBookmarks = inBookmarks))
        }
    }
}

data class ReadingNowUiState(
    val lastBooks: List<Book> = listOf()
)