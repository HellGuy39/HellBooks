package com.hellguy39.hellbooks.ui.component.placeholder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.hellguy39.hellbooks.ui.resources.AppIcons
import com.hellguy39.hellbooks.ui.values.IconSize
import com.hellguy39.hellbooks.ui.values.Spaces

@Composable
fun EmptyContentPlaceholder(
    items: List<Any> = listOf()
) {
    if (items.isNotEmpty()) return
    
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(Spaces.small),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(IconSize.extraLarge),
                painter = painterResource(id = AppIcons.emptyDashboard),
                contentDescription = null
            )
            Text(text = "It's empty here...")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyContentPlaceholderPreview() {
    EmptyContentPlaceholder()
}

