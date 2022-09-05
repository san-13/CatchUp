package com.sv.catchup.ui.home.navigation

sealed class HomeScreens(val route:String) {
    object HomeScreen:HomeScreens("homeScreen")

}