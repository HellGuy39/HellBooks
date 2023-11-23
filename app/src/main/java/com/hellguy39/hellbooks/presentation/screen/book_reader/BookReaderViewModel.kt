package com.hellguy39.hellbooks.presentation.screen.book_reader

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
import javax.inject.Inject

@HiltViewModel
class BookReaderViewModel
@Inject
constructor(
    booksRepository: BookRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val bookId = savedStateHandle.get<Int>(Arguments.Id.key) ?: Arguments.Id.empty

    val uiState = booksRepository.getBookByIdFlow(bookId)
        .map {
            BookReaderUiState(
                book = it ?: Book.emptyInstance()
            )
        }
        .stateIn(
            initialValue = BookReaderUiState(),
            started = SharingStarted.WhileSubscribed(5_000),
            scope = viewModelScope
        )

}

data class BookReaderUiState(
    val book: Book = Book.emptyInstance()
)