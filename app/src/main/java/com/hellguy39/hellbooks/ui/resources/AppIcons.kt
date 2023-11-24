package com.hellguy39.hellbooks.ui.resources

import com.hellguy39.hellbooks.R

object AppIcons {

    val emptyDashboard = R.drawable.baseline_empty_dashboard_24
    val library = R.drawable.baseline_library_books_24
    val menuBook = R.drawable.baseline_menu_book_24
    val arrowBack = R.drawable.baseline_arrow_back_24

    fun bookmark(checked: Boolean): Int {
        return if (checked) R.drawable.baseline_bookmark_24 else R.drawable.baseline_bookmark_border_24
    }

    fun fullscreen(checked: Boolean): Int {
        return if (checked) R.drawable.baseline_open_in_full_24 else R.drawable.baseline_close_fullscreen_24
    }

    fun visibility(isVisible: Boolean): Int {
        return if (isVisible) R.drawable.outline_visibility_24 else R.drawable.outline_visibility_off_24
    }

    val person = R.drawable.baseline_person_24
    val calendarToday = R.drawable.baseline_calendar_today_24
    val category = R.drawable.baseline_category_24
    val done = R.drawable.baseline_done_24
    val doneAll = R.drawable.baseline_done_all_24
    val description = R.drawable.outline_description_24
    val share = R.drawable.baseline_share_24
    val bookmarks = R.drawable.baseline_bookmarks_24

}