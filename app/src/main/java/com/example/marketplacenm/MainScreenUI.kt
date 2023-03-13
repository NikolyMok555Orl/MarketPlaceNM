package com.example.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.authorization.ui.LoginScreenUI
import com.example.authorization.ui.SignScreenUI
import com.example.home.ui.HomeScreenUI
import com.example.item.ui.ItemScreenUI
import com.example.marketplacenm.profile.ui.ProfileScreenUI
import com.example.uigeneral.theme.GreyIcon
import com.example.uigeneral.theme.GreyIconBack
import com.example.uigeneral.theme.MarketPlaceNMTheme


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
    BottomNavigation(contentColor = GreyIcon,backgroundColor = MaterialTheme.colors.onPrimary,
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .height(63.dp)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val selected = currentDestination?.route
        itemsMenu.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.resourceId),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp).clip(CircleShape).
                        background( if (selected == screen.route) GreyIconBack else MaterialTheme.colors.onPrimary)
                            .padding(12.dp)
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
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = GreyIcon
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

