package com.hellguy39.hellbooks.presentation.screen.main.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hellguy39.hellbooks.presentation.navigation.NavigationScreen

@Composable
fun BottomNavigationBar(
    navController: NavController,
    screens: List<NavigationScreen>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        screens.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            NavigationBarItem(
                selected = isSelected,
                onClick = { navController.navigate(screen.route) },
                label = {
                    Text(
                        text = stringResource(id = screen.labelId) 
                    ) 
                },
                icon = { 
                    Icon(
                        painter = painterResource(id = screen.iconId), 
                        contentDescription = null 
                    )
                }
            )
        }
    }
}