package com.example.marketplacenm.navigation

import androidx.annotation.StringRes
import com.example.marketplacenm.R


sealed class Screen(val route: String,  val resourceId: Int) {
    object Home : Screen("home", R.drawable.menu_home)
    object Favorite : Screen("favorite", R.drawable.menu_favorite)
    object Cart : Screen("cart", R.drawable.menu_cart)
    object Message : Screen("message", R.drawable.menu_messages)
    object Profile : Screen("profile", R.drawable.menu_user)
}


val itemsMenu = listOf(
    Screen.Home,
    Screen.Favorite,
    Screen.Cart,
    Screen.Message,
    Screen.Profile,

)