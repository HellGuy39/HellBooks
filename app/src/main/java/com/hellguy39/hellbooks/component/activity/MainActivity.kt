package com.hellguy39.hellbooks.component.activity

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.hellguy39.hellbooks.presentation.navigation.graph.GlobalNavGraph
import com.hellguy39.hellbooks.ui.theme.HellBooksTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransparentSystemBars(true)
        setContent { ApplicationContent() }
        mainActivityViewModel.prepareData()
    }
}

fun Activity.setTransparentSystemBars(isTransparent: Boolean) {
    WindowCompat.setDecorFitsSystemWindows(window, isTransparent.not())
}

@Composable
fun ApplicationContent() {
    HellBooksTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            GlobalNavGraph()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ApplicationContentPreview() {
    ApplicationContent()
}