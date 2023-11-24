package com.hellguy39.hellbooks.presentation.screen.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun RegisterRoute(
    registerViewModel: RegisterViewModel = hiltViewModel(),
    navigateToMain: () -> Unit,
    navigateBack: () -> Unit
) {
    val uiState by registerViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = uiState.isAuthenticated) {
        if (uiState.isAuthenticated) {
            navigateToMain()
        }
    }

    RegisterScreen(
        uiState = uiState,
        onNavigationClick = { navigateBack() },
        onRegisterClick = { registerViewModel.register() },
        onLoginEdited = { text -> registerViewModel.editLogin(text) },
        onPasswordEdited = { text -> registerViewModel.editPassword(text)}
    )
}