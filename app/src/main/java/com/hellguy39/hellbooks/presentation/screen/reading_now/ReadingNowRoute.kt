package com.hellguy39.hellbooks.presentation.screen.reading_now

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ReadingNowRoute(
    readingNowViewModel: ReadingNowViewModel = hiltViewModel(),
    navigateToBookDetail: (id: Int) -> Unit
) {
    val uiState by readingNowViewModel.uiState.collectAsStateWithLifecycle()

    ReadingNowScreen(
        uiState = uiState,
        onBookClick = { book ->
            navigateToBookDetail(book.id)
        },
        onToggleBookmark = { book ->
            readingNowViewModel.toggleBookmark(book)
        }
    )
}