package com.hellguy39.hellbooks.presentation.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.hellguy39.hellbooks.presentation.navigation.graph.MainNavGraph
import com.hellguy39.hellbooks.presentation.screen.main.component.BottomNavigationBar

@Composable
fun MainRoute(
    navigateToBookDetail: (id: Int) -> Unit
) {
    val screens = rememberMainScreens()
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController, screens) }
    ) { innerPadding ->
        MainNavGraph(
            modifier = Modifier.fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding()),
            navController = navController,
            navigateToBookDetail = navigateToBookDetail
        )
    }
}