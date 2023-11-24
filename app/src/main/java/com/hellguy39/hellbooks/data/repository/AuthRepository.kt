package com.hellguy39.hellbooks.data.repository

import com.hellguy39.hellbooks.data.mapper.toUser
import com.hellguy39.hellbooks.database.dao.UserDao
import com.hellguy39.hellbooks.database.entity.UserEntity
import com.hellguy39.hellbooks.domain.AuthRepository
import com.hellguy39.hellbooks.model.LoginParams
import com.hellguy39.hellbooks.model.RegisterParams
import com.hellguy39.hellbooks.model.User
import com.hellguy39.hellbooks.prefs.PrefsStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl
@Inject
constructor(
    private val userDao: UserDao,
    private val localStorage: PrefsStorage
): AuthRepository {

    override suspend fun getCurrentUser(): User? {
        if (!isAuthenticated()) return null
        return userDao.findById(localStorage.authenticatedUserid)?.toUser()
    }

    override fun getCurrentUserFlow(): Flow<User?> {
        return userDao.findByIdFlow(localStorage.authenticatedUserid)
            .map { it?.toUser() }
    }

    override suspend fun register(registerParams: RegisterParams): Boolean {
        delay(FAKE_DELAY)

        if (userDao.findByLogin(registerParams.login) != null) return false

        val userEntity = UserEntity(
            login = registerParams.login,
            password = registerParams.password,
        )
        val id = userDao.insert(userEntity).toInt()
        localStorage.authenticatedUserid = id
        return true
    }

    override suspend fun login(loginParams: LoginParams): Boolean {
        delay(FAKE_DELAY)

        val fundedUserEntity = userDao.findByLogin(loginParams.login) ?: return false

        return if (fundedUserEntity.password == loginParams.password) {
            localStorage.authenticatedUserid = fundedUserEntity.id
            true
        } else {
            false
        }
    }

    override suspend fun isAuthenticated(): Boolean {
        return localStorage.authenticatedUserid != PrefsStorage.DefaultValues.emptyUserId
    }

    override suspend fun logout() {
        localStorage.authenticatedUserid = PrefsStorage.DefaultValues.emptyUserId
    }

    companion object {
        private const val FAKE_DELAY = 3_000L
    }
}