package com.hellguy39.hellbooks.domain

import com.hellguy39.hellbooks.model.LoginParams
import com.hellguy39.hellbooks.model.RegisterParams
import com.hellguy39.hellbooks.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun getCurrentUser(): User?

    fun getCurrentUserFlow(): Flow<User?>

    suspend fun register(registerParams: RegisterParams): Boolean

    suspend fun login(loginParams: LoginParams): Boolean

    suspend fun isAuthenticated(): Boolean

    suspend fun logout()

}