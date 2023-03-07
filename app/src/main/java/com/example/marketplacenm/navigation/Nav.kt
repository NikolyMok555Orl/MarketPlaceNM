package com.example.marketplacenm.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marketplacenm.MainScreen
import com.example.marketplacenm.authorization.ui.LoginScreenUI
import com.example.marketplacenm.authorization.ui.SignScreenUI


/*@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = SIGN
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(SIGN) {
            SignScreenUI(navController)
        }
        composable(LOGIN) {
            LoginScreenUI(navController)
        }
        composable(MAIN) {
            MainScreen(navController)
        }
    }
}*/

