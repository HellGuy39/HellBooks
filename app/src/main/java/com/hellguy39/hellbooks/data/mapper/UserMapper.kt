package com.hellguy39.hellbooks.data.mapper

import com.hellguy39.hellbooks.database.entity.UserEntity
import com.hellguy39.hellbooks.model.User

fun UserEntity.toUser(): User {
    return User(
        id = id,
        login = login,
        password = password
    )
}