package com.hellguy39.hellbooks.presentation.screen.reading_now

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hellguy39.hellbooks.model.Book
import com.hellguy39.hellbooks.ui.component.item.BookItem
import com.hellguy39.hellbooks.ui.component.placeholder.EmptyContentPlaceholder
import com.hellguy39.hellbooks.ui.values.Spaces

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingNowScreen(
    uiState: ReadingNowUiState = ReadingNowUiState(),
    onBookClick: (book: Book) -> Unit = {},
    onToggleBookmark: (book: Book) -> Unit = {}
) {
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = "Reading now")
                }
            )
        }
    ) { innerPadding ->
        EmptyContentPlaceholder(uiState.lastBooks)

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding() + Spaces.medium,
                bottom = Spaces.medium,
                start = Spaces.medium,
                end = Spaces.medium
            ),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(Spaces.medium),
            verticalArrangement = Arrangement.spacedBy(Spaces.medium)
        ) {
            items(
                items = uiState.lastBooks,
                key = { book -> book.id }
            ) { book ->
                BookItem(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onBookClick(book) },
                    onToggleBookmark = { onToggleBookmark(book) },
                    book = book,
                )
            }
        }
    }
}