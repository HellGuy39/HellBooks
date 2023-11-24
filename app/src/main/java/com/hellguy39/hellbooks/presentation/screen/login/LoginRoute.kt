package com.hellguy39.hellbooks.presentation.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LoginRoute(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigateToMain: () -> Unit,
    navigateToRegister: () -> Unit
) {
    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = uiState.isAuthenticated) {
        if (uiState.isAuthenticated) {
            navigateToMain()
        }
    }

    LoginScreen(
        uiState = uiState,
        onLoginClick = { loginViewModel.login() },
        onRegisterClick = { navigateToRegister() },
        onLoginEdited = { text -> loginViewModel.editLogin(text) },
        onPasswordEdited = { text -> loginViewModel.editPassword(text) },
    )
}