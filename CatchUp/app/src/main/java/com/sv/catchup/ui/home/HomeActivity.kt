package com.sv.catchup.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Surface
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.sv.catchup.ui.home.navigation.HomeNavigation
import com.sv.catchup.ui.home.navigation.HomeScreens
import com.sv.catchup.ui.theme.CatchUpTheme

class HomeActivity : ComponentActivity() {
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
                        startDestination = HomeScreens.HomeScreen.route
                    )
                }
            }
        }
    }
}