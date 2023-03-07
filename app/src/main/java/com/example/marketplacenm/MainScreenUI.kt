package com.example.marketplacenm

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
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
import com.example.marketplacenm.navigation.*
import com.example.marketplacenm.profile.ui.ProfileScreenUI
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme


@Composable
fun MainScreen(navController: NavHostController = rememberNavController(), modifier: Modifier=Modifier){
    val showMenu= remember {
        mutableStateOf(false)
    }

    fun setShowMenu(){
        showMenu.value=true
    }

    fun setHideMenu(){
        showMenu.value=false
    }

    MarketPlaceNMTheme() {
        Scaffold(
            bottomBar = {
                if(showMenu.value) {
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
            }
        ) { innerPadding ->
            NavHost(
                navController,
                startDestination = SIGN,
                Modifier.padding(innerPadding)
            ) {
                composable(Screen.Home.route) {
                    setShowMenu()
                    HomeScreenUI(navController)

                }
                composable(Screen.Favorite.route) {
                    setShowMenu()
                    EmptyScreenUI("Favorite")
                }
                composable(Screen.Cart.route) {
                    setShowMenu()
                    EmptyScreenUI("Cart")
                }
                composable(Screen.Message.route) {
                    setShowMenu()
                    EmptyScreenUI("Message")
                }
                composable(Screen.Profile.route) {
                    setShowMenu()
                    ProfileScreenUI(navController)
                }
                composable(SIGN) {
                    setHideMenu()
                    SignScreenUI(navController)
                }
                composable(LOGIN) {
                    setHideMenu()
                    LoginScreenUI(navController)
                }
                composable(MAIN) {
                    MainScreen(navController)
                }
            }
        }

    }
}


@Composable
fun EmptyScreenUI(title: String){
    Box(contentAlignment = Alignment.Center,modifier = Modifier.fillMaxSize()) {
        Text(text = title)
    }
}

const val SIGN="Sign"
const val LOGIN="Login"
const val MAIN="Main"