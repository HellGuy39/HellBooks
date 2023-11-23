package com.hellguy39.hellbooks.presentation.screen.bookmarks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun BookmarksRoute(
    bookmarksViewModel: BookmarksViewModel = hiltViewModel(),
    navigateToBookDetail: (id: Int) -> Unit
) {
    val uiState by bookmarksViewModel.uiState.collectAsStateWithLifecycle()

    BookmarksScreen(
        uiState = uiState,
        onToggleBookmark = { book -> bookmarksViewModel.toggleBookmark(book) },
        onBookClick = { book -> navigateToBookDetail(book.id) }
    )
}