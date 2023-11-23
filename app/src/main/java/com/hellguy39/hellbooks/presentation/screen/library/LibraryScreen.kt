package com.hellguy39.hellbooks.presentation.screen.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hellguy39.hellbooks.model.Book
import com.hellguy39.hellbooks.ui.component.item.BookItem
import com.hellguy39.hellbooks.ui.component.placeholder.EmptyContentPlaceholder
import com.hellguy39.hellbooks.ui.resources.AppIcons
import com.hellguy39.hellbooks.ui.values.Spaces

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    uiState: LibraryUiState = LibraryUiState(),
    onBookClick: (book: Book) -> Unit = {},
    onToggleBookmark: (book: Book) -> Unit = {}
) {
    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(appBarState)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = "Library")
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        EmptyContentPlaceholder(uiState.books)
        
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
                items = uiState.books,
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

@Preview
@Composable
fun LibraryScreenPreview() {
    LibraryScreen()
}

