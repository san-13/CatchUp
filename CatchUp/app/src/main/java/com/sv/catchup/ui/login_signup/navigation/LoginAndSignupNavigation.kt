package com.sv.catchup.ui.login_signup.navigation

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.sv.catchup.ui.login_signup.screens.LoginScreen
import com.sv.catchup.ui.login_signup.viewmodel.loginViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LoginAndSignupNavigation(
    navController: NavHostController,
    startDestination: String = LoginAndSignupScreens.LoginScreen.route,
    context: Context,
    loginVm: loginViewModel
){
    AnimatedNavHost(navController = navController, startDestination = startDestination){
        composable(
            route = LoginAndSignupScreens.LoginScreen.route,
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
            LoginScreen(loginVm)
        }
    }

}