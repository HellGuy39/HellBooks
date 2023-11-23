package com.hellguy39.hellbooks.presentation.screen.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.hellguy39.hellbooks.presentation.navigation.NavigationScreen
import com.hellguy39.hellbooks.presentation.navigation.route.MainScreen
import com.hellguy39.hellbooks.ui.resources.AppIcons
import com.hellguy39.hellbooks.ui.resources.AppStrings

@Composable
fun rememberMainScreens(): List<NavigationScreen> {
    return remember {
        listOf(
            NavigationScreen(
                route = MainScreen.ReadingNow.route,
                iconId = AppIcons.menuBook,
                labelId = AppStrings.Labels.ReadingNow,
            ),
            NavigationScreen(
                route = MainScreen.Library.route,
                iconId = AppIcons.library,
                labelId = AppStrings.Labels.Library,
            ),
            NavigationScreen(
                route = MainScreen.Bookmarks.route,
                iconId = AppIcons.bookmarks,
                labelId = AppStrings.Labels.Bookmarks,
            )
        )
    }
}