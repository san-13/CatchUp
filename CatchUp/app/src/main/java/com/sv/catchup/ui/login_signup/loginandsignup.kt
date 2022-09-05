package com.sv.catchup.ui.login_signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Surface
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.sv.catchup.ui.login_signup.navigation.LoginAndSignupNavigation
import com.sv.catchup.ui.login_signup.navigation.LoginAndSignupScreens
import com.sv.catchup.ui.login_signup.viewmodel.loginViewModel
import com.sv.catchup.ui.theme.CatchUpTheme

class loginandsignup : ComponentActivity() {

    val loginViewModel: loginViewModel by viewModels()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatchUpTheme() {
                Surface {
                    val navController = rememberAnimatedNavController()
                    LoginAndSignupNavigation(
                        navController = navController,
                        context = this,
                        loginVm = loginViewModel,
                        startDestination = LoginAndSignupScreens.GetStartedScreen.route
                    )
                }
            }
        }
    }
}
