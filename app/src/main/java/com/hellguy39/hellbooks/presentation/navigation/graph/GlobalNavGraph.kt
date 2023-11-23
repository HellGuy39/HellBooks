package com.hellguy39.hellbooks.presentation.navigation.graph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hellguy39.hellbooks.presentation.navigation.route.GlobalScreen
import com.hellguy39.hellbooks.presentation.screen.book_detail.BookDetailRoute
import com.hellguy39.hellbooks.presentation.screen.book_reader.BookReaderRoute
import com.hellguy39.hellbooks.presentation.screen.main.MainRoute
import com.hellguy39.hellbooks.ui.values.AnimDuration

@Composable
fun GlobalNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = GlobalScreen.Main.route
    ) {
        composable(GlobalScreen.Login.route) {

        }
        composable(GlobalScreen.Register.route) {

        }
        composable(
            route = GlobalScreen.BookDetail.route,
            arguments = GlobalScreen.BookDetail.listOfArgs(),
            enterTransition = {
                when(initialState.destination.route) {
                    GlobalScreen.Main.route -> {
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                            animationSpec = tween(AnimDuration.medium)
                        )
                    }
                    else -> null
                }
            },
            exitTransition = {
                when(targetState.destination.route) {
                    GlobalScreen.Main.route -> {
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                            animationSpec = tween(AnimDuration.fast)
                        )
                    }
                    else -> null
                }
            },
        ) {
            BookDetailRoute(
                navigateBack = { navController.popBackStack() },
                navigateToBookReader = { id ->
                    navController.navigate(GlobalScreen.BookReader.passId(id))
                }
            )
        }
        composable(
            route = GlobalScreen.Main.route
        ) {
            MainRoute(
                navigateToBookDetail = { id ->
                    navController.navigate(GlobalScreen.BookDetail.passId(id))
                }
            )
        }
        composable(
            route = GlobalScreen.BookReader.route,
            arguments = GlobalScreen.BookReader.listOfArgs(),
            enterTransition = {
                when(initialState.destination.route) {
                    GlobalScreen.BookDetail.route -> {
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                            animationSpec = tween(AnimDuration.medium)
                        )
                    }
                    else -> null
                }
            },
            exitTransition = {
                when(targetState.destination.route) {
                    GlobalScreen.BookDetail.route -> {
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                            animationSpec = tween(AnimDuration.fast)
                        )
                    }
                    else -> null
                }
            },
        ) {
            BookReaderRoute(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}