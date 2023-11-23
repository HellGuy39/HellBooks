package com.hellguy39.hellbooks.presentation.screen.book_detail

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun BookDetailRoute(
    bookDetailViewModel: BookDetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    navigateToBookReader: (id: Int) -> Unit
) {
    val uiState by bookDetailViewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    BookDetailScreen(
        uiState = uiState,
        onNavigationClick = { navigateBack() },
        onOpenBookClick = {
            bookDetailViewModel.startReadingBook()
            navigateToBookReader(uiState.book.id)
        },
        onToggleBookmark = {
            bookDetailViewModel.toggleBookmark()
        },
        onToggleMarkAsRead = {
            bookDetailViewModel.toggleMarkAsRead()
        },
        onShareClick = {
            val book = uiState.book
            val intent = Intent(Intent.ACTION_SEND)
            with(intent) {
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_SUBJECT,
                    book.name
                )
                putExtra(
                    Intent.EXTRA_TEXT,
                    "${book.name} - ${book.author}, ${book.date}. ${book.description}"
                )
            }
            ContextCompat.startActivity(
                context, Intent.createChooser(intent, "Share"), null
            )
        }
    )
}