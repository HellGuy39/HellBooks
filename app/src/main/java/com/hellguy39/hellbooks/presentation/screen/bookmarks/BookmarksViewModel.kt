package com.hellguy39.hellbooks.presentation.screen.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellguy39.hellbooks.domain.BookRepository
import com.hellguy39.hellbooks.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel
@Inject
constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    val uiState = bookRepository.bookmarks
        .map { books ->
            BookmarksUiState(
                bookmarks = books
            )
        }
        .stateIn(
            initialValue = BookmarksUiState(),
            started = SharingStarted.WhileSubscribed(5_000),
            scope = viewModelScope
        )

    fun toggleBookmark(book: Book) {
        viewModelScope.launch {
            val inBookmarks = book.inBookmarks.not()
            bookRepository.updateBook(book = book.copy(inBookmarks = inBookmarks))
        }
    }

}

data class BookmarksUiState(
    val bookmarks: List<Book> = listOf()
)