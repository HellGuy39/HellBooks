package com.hellguy39.hellbooks.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hellguy39.hellbooks.presentation.navigation.route.MainScreen
import com.hellguy39.hellbooks.presentation.screen.bookmarks.BookmarksRoute
import com.hellguy39.hellbooks.presentation.screen.library.LibraryRoute
import com.hellguy39.hellbooks.presentation.screen.reading_now.ReadingNowRoute

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navigateToBookDetail: (id: Int) -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MainScreen.ReadingNow.route
    ) {
        composable(MainScreen.ReadingNow.route) {
            ReadingNowRoute(
                navigateToBookDetail = navigateToBookDetail
            )
        }
        composable(MainScreen.Library.route) {
            LibraryRoute(
                navigateToBookDetail = navigateToBookDetail
            )
        }
        composable(MainScreen.Bookmarks.route) {
            BookmarksRoute(
                navigateToBookDetail = navigateToBookDetail
            )
        }
    }
}