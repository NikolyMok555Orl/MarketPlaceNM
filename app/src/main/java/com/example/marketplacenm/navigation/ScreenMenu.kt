package com.example.marketplacenm.navigation

import com.example.marketplacenm.R





sealed class Screen(val route: String, val showNavBotton:Boolean=true) {
    object  Sign:Screen("sign", false)
    object  Login:Screen("Login", false)
    object  Main:Screen("main", false)

    object  Item:Screen("item" )

    sealed class ScreenMenu(route: String, val resourceId: Int):Screen(route) {
        object Home : ScreenMenu("home", R.drawable.menu_home)
        object Favorite : ScreenMenu("favorite", R.drawable.menu_favorite)
        object Cart : ScreenMenu("cart", R.drawable.menu_cart)
        object Message : ScreenMenu("message", R.drawable.menu_messages)
        object Profile : ScreenMenu("profile", R.drawable.menu_user)
    }
}


val itemsMenu = listOf(
    Screen.ScreenMenu.Home,
    Screen.ScreenMenu.Favorite,
    Screen.ScreenMenu.Cart,
    Screen.ScreenMenu.Message,
    Screen.ScreenMenu.Profile,

)


val allScreen= (mutableListOf(
    Screen.Main, Screen.Sign, Screen.Login, Screen.Item
).apply {
    addAll(itemsMenu)
}).toList()
