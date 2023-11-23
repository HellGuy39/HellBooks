package com.hellguy39.hellbooks.presentation.screen.library

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LibraryRoute(
    libraryViewModel: LibraryViewModel = hiltViewModel(),
    navigateToBookDetail: (id: Int) -> Unit
) {
    val uiState by libraryViewModel.uiState.collectAsStateWithLifecycle()

    LibraryScreen(
        uiState = uiState,
        onBookClick = { book ->
            navigateToBookDetail(book.id)
        },
        onToggleBookmark = { book ->
            libraryViewModel.toggleBookmark(book)
        }
    )
}