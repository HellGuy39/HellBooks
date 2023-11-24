package com.hellguy39.hellbooks.presentation.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellguy39.hellbooks.domain.AuthRepository
import com.hellguy39.hellbooks.model.RegisterParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
@Inject
constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun register() {
        if (_uiState.value.isLoading) return
        toggleLoading()

        viewModelScope.launch {
            val params = RegisterParams(
                login = _uiState.value.login,
                password = _uiState.value.password,
            )
            val isAuthenticated = authRepository.register(params)
            _uiState.update { state -> state.copy(isAuthenticated = isAuthenticated) }
            toggleLoading()
        }
    }

    fun editLogin(login: String) {
        _uiState.update { state -> state.copy(login = login) }
    }

    fun editPassword(password: String) {
        _uiState.update { state -> state.copy(password = password) }
    }

    private fun toggleLoading() {
        _uiState.update { state -> state.copy(isLoading = state.isLoading.not()) }
    }
}

data class RegisterUiState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false,
    val login: String = "",
    val password: String = "",
)