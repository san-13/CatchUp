package com.sv.catchup.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Surface
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.sv.catchup.ui.home.navigation.HomeNavigation
import com.sv.catchup.ui.home.navigation.HomeScreens
import com.sv.catchup.ui.theme.CatchUpTheme
import com.sv.catchup.ui.videoCall.viewModel.videoViewModel

const val APP_ID = "3f7864367b3e439bb99a4cd31252c1de"
class HomeActivity : ComponentActivity() {
    val homeViewModel:homeViewModel by viewModels()
    val videoViewModel:videoViewModel by viewModels()
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatchUpTheme {
                Surface {
                    val navHostController = rememberAnimatedNavController()
                    HomeNavigation(
                        navController = navHostController,
                        context = this,
                        startDestination = HomeScreens.HomeScreen.route,
                        homeViewModel = homeViewModel,
                        videoViewModel = videoViewModel
                    )
                }
            }
        }
    }
}