package com.hellguy39.hellbooks.data.mapper

import com.hellguy39.hellbooks.database.entity.BookEntity
import com.hellguy39.hellbooks.model.Book

fun Book.toBookEntity(): BookEntity {
    return BookEntity(
        id = id,
        name = name,
        author = author,
        date = date,
        genre = genre,
        description = description,
        previewUrl = previewUrl,
        bookUrl = bookUrl,
        inBookmarks = inBookmarks,
        isRead = isRead,
    )
}

fun BookEntity.toBook(): Book {
    return Book(
        id = id,
        name = name,
        author = author,
        date = date,
        genre = genre,
        description = description,
        previewUrl = previewUrl,
        bookUrl = bookUrl,
        inBookmarks = inBookmarks,
        isRead = isRead,
    )
}
