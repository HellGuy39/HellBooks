package com.hellguy39.hellbooks.presentation.screen.book_reader

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.hellguy39.hellbooks.common.extendUrlWithViewer
import com.hellguy39.hellbooks.ui.resources.AppIcons

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun BookReaderScreen(
    uiState: BookReaderUiState = BookReaderUiState(),
    onNavigationClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = uiState.book.name)
                },
                navigationIcon = {
                    IconButton(onClick = onNavigationClick) {
                        Icon(
                            painter = painterResource(id = AppIcons.arrowBack),
                            contentDescription = null
                        )
                    }
                },
//                actions = {
//                    IconButton(onClick = { }) {
//                        Icon(
//                            painter = painterResource(id = AppIcons.fullscreen(true)),
//                            contentDescription = null
//                        )
//                    }
//                }
            )
        }
    ) { innerPadding ->
        if (uiState.book.bookUrl.isNotEmpty()) {
            val webViewState = rememberWebViewState(url = extendUrlWithViewer(uiState.book.bookUrl))
            WebView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                state = webViewState,
                onCreated = { webView ->
                    with(webView) {
                        settings.javaScriptEnabled = true
                    }
                },
                captureBackPresses = false
            )
        }
    }
}