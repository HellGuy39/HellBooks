package com.hellguy39.hellbooks.presentation.screen.book_reader

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun BookReaderRoute(
    bookReaderViewModel: BookReaderViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val uiState by bookReaderViewModel.uiState.collectAsStateWithLifecycle()

    BookReaderScreen(
        uiState = uiState,
        onNavigationClick = navigateBack
    )
}