package com.example.marketplacenm

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.marketplacenm.authorization.ui.LoginScreenUI
import com.example.marketplacenm.authorization.ui.SignScreenUI
import com.example.marketplacenm.home.ui.HomeScreenUI
import com.example.marketplacenm.item.ui.ItemScreenUI
import com.example.marketplacenm.navigation.*
import com.example.marketplacenm.profile.ui.ProfileScreenUI
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme


@Composable
fun MainScreen(navController: NavHostController = rememberNavController(), modifier: Modifier=Modifier){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    MarketPlaceNMTheme() {
        Scaffold(
            bottomBar = {
                if( allScreen.find {it.route==currentDestination?.route  }?.showNavBotton==true) {
                    AppBottonNavigationUI(navController)
                }
            }
        ) { innerPadding ->
            NavHost(
                navController,
                startDestination = Screen.Sign.route,
                Modifier.padding(innerPadding)
            ) {
                composable(Screen.ScreenMenu.Home.route) {

                    HomeScreenUI(navController)

                }
                composable(Screen.ScreenMenu.Favorite.route) {

                    EmptyScreenUI("Favorite")
                }
                composable(Screen.ScreenMenu.Cart.route) {

                    EmptyScreenUI("Cart")
                }
                composable(Screen.ScreenMenu.Message.route) {
                    EmptyScreenUI("Message")
                }
                composable(Screen.ScreenMenu.Profile.route) {
                    ProfileScreenUI(navController)
                }
                composable(Screen.Sign.route) {
                    SignScreenUI(navController)
                }
                composable(Screen.Login.route) {
                    LoginScreenUI(navController)
                }
                composable(Screen.Main.route) {
                    MainScreen(navController)
                }

                composable(Screen.Item.route) {
                    ItemScreenUI(navController)
                }
            }
        }

    }
}

@Composable
private fun AppBottonNavigationUI(navController: NavHostController) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        itemsMenu.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.resourceId),
                        contentDescription = null
                    )
                },
                label = null,
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}


@Composable
fun EmptyScreenUI(title: String){
    Box(contentAlignment = Alignment.Center,modifier = Modifier.fillMaxSize()) {
        Text(text = title)
    }
}

