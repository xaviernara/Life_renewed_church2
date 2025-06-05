package com.example.life_renewed.navigation

import kotlinx.serialization.Serializable

sealed class NavScreens(val route: String, val title: String) {
//    data class About(var id: Int, var name: String) : NavScreens("about_screen", "About")
    data object About : NavScreens("about_screen", "About")
    data object Announcements : NavScreens("announcements_screen", "announcements")
    data object ConnectForm : NavScreens("connect_screen", "Connect")
    data object Home : NavScreens("home_screen", "Home")
    data object Bulletin : NavScreens("bulletin_screen", "Bulletin")
    data object Giving : NavScreens("giving_screen", "Give")
    data object Map : NavScreens("map_screen", "Map")
    data object Splash : NavScreens("splash_screen", "Splash")
    data object Links : NavScreens("links_screen", "Links")
}