package com.hellguy39.hellbooks.presentation.navigation.route

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.hellguy39.hellbooks.presentation.navigation.Arguments
import com.hellguy39.hellbooks.presentation.navigation.argument

sealed class GlobalScreen(val route: String) {

    data object Login: GlobalScreen("login")

    data object Register: GlobalScreen("register")

    data object Main: GlobalScreen("main")

    data object BookReader: GlobalScreen("book_reader/${argument(Arguments.Id.key)}") {

        fun passId(id: Int): String {
            return this.route.replace(oldValue = argument(Arguments.Id.key), newValue = id.toString())
        }

        fun listOfArgs() = listOf(
            navArgument(Arguments.Id.key) { type = NavType.IntType }
        )
    }

    data object BookDetail: GlobalScreen("book/${argument(Arguments.Id.key)}") {

        fun passId(id: Int): String {
            return this.route.replace(oldValue = argument(Arguments.Id.key), newValue = id.toString())
        }

        fun listOfArgs() = listOf(
            navArgument(Arguments.Id.key) { type = NavType.IntType }
        )
    }
}