package com.sv.catchup.ui.home.navigation

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.sv.catchup.ui.home.homeViewModel
import com.sv.catchup.ui.home.screens.EnterMeetCodeScreen
import com.sv.catchup.ui.home.screens.HomeScreen
import com.sv.catchup.ui.videoCall.screens.VideoScreen
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
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
                ) + fadeOut(animationSpec = tween(durationMillis = 300))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(durationMillis = 300))
            }
        ) {
            HomeScreen(navController,homeViewModel, onNavigate = navController::navigate, context = context)
        }
        composable(
            route = HomeScreens.EnterMeetingCodeScreen.route
        ) {
            EnterMeetCodeScreen(navController=navController)
        }
    }
}