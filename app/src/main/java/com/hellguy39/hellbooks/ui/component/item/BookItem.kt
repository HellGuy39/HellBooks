package com.hellguy39.hellbooks.ui.component.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hellguy39.hellbooks.data.fake.fakeBooks
import com.hellguy39.hellbooks.model.Book
import com.hellguy39.hellbooks.ui.resources.AppIcons
import com.hellguy39.hellbooks.ui.values.Spaces

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onToggleBookmark: () -> Unit = {},
    book: Book
) {
    ElevatedCard(
        modifier = modifier,
        onClick = onClick
    ) {
        Box(modifier = Modifier) {
            AsyncImage(
                modifier = Modifier
                    .height(220.dp)
                    .clip(MaterialTheme.shapes.medium),
                model = book.previewUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            IconToggleButton(
                modifier = Modifier.align(Alignment.TopEnd),
                checked = book.inBookmarks,
                onCheckedChange = { onToggleBookmark() }
            ) {
                Icon(
                    painter = painterResource(id = AppIcons.bookmark(book.inBookmarks)),
                    contentDescription = null
                )
            }
        }
        Column(
            modifier = Modifier.padding(Spaces.medium),
            verticalArrangement = Arrangement.spacedBy(Spaces.medium)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(Spaces.small)
            ) {
                Text(
                    text = book.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = book.author,
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookItemPreview() {
    BookItem(
        book = fakeBooks.first()
    )
}