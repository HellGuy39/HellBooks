package com.hellguy39.hellbooks.presentation.screen.book_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hellguy39.hellbooks.ui.resources.AppIcons
import com.hellguy39.hellbooks.ui.values.IconSize
import com.hellguy39.hellbooks.ui.values.Spaces

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BookDetailScreen(
    uiState: BookDetailUiState = BookDetailUiState(),
    onNavigationClick: () -> Unit = {},
    onOpenBookClick: () -> Unit = {},
    onToggleBookmark: () -> Unit = {},
    onToggleMarkAsRead: () -> Unit = {},
    onShareClick: () -> Unit = {}
) {
    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(appBarState)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {  },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigationClick
                    ) {
                        Icon(
                            painter = painterResource(id = AppIcons.arrowBack),
                            contentDescription = null 
                        )
                    }
                },
                actions = {
                    IconToggleButton(
                        modifier = Modifier,
                        checked = uiState.book.inBookmarks,
                        onCheckedChange = { onToggleBookmark() }
                    ) {
                        Icon(
                            painter = painterResource(id = AppIcons.bookmark(uiState.book.inBookmarks)),
                            contentDescription = null
                        )
                    }
                    IconButton(
                        modifier = Modifier,
                        onClick = onShareClick
                    ) {
                        Icon(
                            painter = painterResource(id = AppIcons.share),
                            contentDescription = null
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding() + Spaces.medium,
                start = Spaces.medium,
                end = Spaces.medium,
                bottom = innerPadding.calculateBottomPadding() + Spaces.medium
            ),
            verticalArrangement = Arrangement.spacedBy(Spaces.medium)
        ) {
            item {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Spaces.extraLarge + Spaces.extraLarge)
                        .aspectRatio(1f / 1.5f)
                        .clip(MaterialTheme.shapes.medium),
                    model = uiState.book.previewUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(Spaces.small, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = uiState.book.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = uiState.book.author,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                        //.padding(horizontal = Spaces.medium),
                    horizontalArrangement = Arrangement.spacedBy(Spaces.medium),
                ) {
                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        onClick = { onToggleMarkAsRead() }
                    ) {
                        Icon(
                            modifier = Modifier.size(ButtonDefaults.IconSize),
                            painter = painterResource(id = AppIcons.doneAll),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                        Text(
                            text = if (uiState.book.isRead) "Mark as unread" else "Mark as read"
                        )
                    }
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = onOpenBookClick
                    ) {
                        Icon(
                            modifier = Modifier.size(ButtonDefaults.IconSize),
                            painter = painterResource(id = AppIcons.menuBook),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                        Text(text = "Start reading")
                    }
                }
            }
            item {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(Spaces.medium, Alignment.CenterVertically),
                    horizontalArrangement = Arrangement.spacedBy(Spaces.medium, Alignment.CenterHorizontally)
                ) {
                    AssistChip(
                        modifier = Modifier.height(AssistChipDefaults.Height),
                        onClick = {  },
                        label = { Text(text = uiState.book.genre) },
                        leadingIcon = {
                            Icon(
                                modifier = Modifier.size(AssistChipDefaults.IconSize),
                                painter = painterResource(id = AppIcons.category), 
                                contentDescription = null 
                            )
                        }
                    )
                    AssistChip(
                        modifier = Modifier.height(AssistChipDefaults.Height),
                        onClick = {  },
                        label = { Text(text = uiState.book.date) },
                        leadingIcon = {
                            Icon(
                                modifier = Modifier.size(AssistChipDefaults.IconSize),
                                painter = painterResource(id = AppIcons.calendarToday),
                                contentDescription = null
                            )
                        }
                    )
                }
            }
            item {
                ElevatedCard {
                    Column(
                        modifier = Modifier.padding(Spaces.medium),
                        verticalArrangement = Arrangement.spacedBy(Spaces.small)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(IconSize.small),
                                painter = painterResource(id = AppIcons.description),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(Spaces.small))
                            Text(
                                text = "Description",
                                style = MaterialTheme.typography.titleSmall
                            )
                        }

                        Divider()

                        Text(
                            text = uiState.book.description,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}