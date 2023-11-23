package com.hellguy39.hellbooks.presentation.navigation.route


sealed class MainScreen(val route: String) {

    data object ReadingNow: MainScreen("reading_now")

    data object Library: MainScreen("library")

    data object Bookmarks: MainScreen("bookmarks")

}