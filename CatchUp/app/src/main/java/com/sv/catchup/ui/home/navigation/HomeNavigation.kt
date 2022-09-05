package com.sv.catchup.ui.home.navigation

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.sv.catchup.ui.home.homeViewModel
import com.sv.catchup.ui.home.screens.HomeScreen
import com.sv.catchup.ui.login_signup.screens.GetStartedScreen
import com.sv.catchup.ui.videoCall.viewModel.videoViewModel

@OptIn(ExperimentalAnimationApi::class, ExperimentalUnsignedTypes::class)
@Composable
fun HomeNavigation(
    navController: NavHostController,
    startDestination: String = HomeScreens.HomeScreen.route,
    context: Context,
    homeViewModel: homeViewModel,
    videoViewModel: videoViewModel
){
    AnimatedNavHost(navController = navController, startDestination = startDestination){
        composable(
            route = HomeScreens.HomeScreen.route,

        ) {
            HomeScreen(navController,homeViewModel, onNavigate = navController::navigate, context = context)
        }

    }
}