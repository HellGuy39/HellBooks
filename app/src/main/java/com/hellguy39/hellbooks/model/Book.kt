package com.hellguy39.hellbooks.model

data class Book(
    val id: Int,
    val name: String,
    val author: String,
    val date: String,
    val genre: String,
    val description: String,
    val previewUrl: String,
    val bookUrl: String,
    val inBookmarks: Boolean,
    val isRead: Boolean,
) {
    companion object {
        fun emptyInstance(): Book {
            return Book(
                id = 0,
                name = "",
                author = "",
                date = "",
                genre = "",
                description = "",
                previewUrl = "",
                bookUrl = "",
                isRead = false,
                inBookmarks = false
            )
        }
    }
}
