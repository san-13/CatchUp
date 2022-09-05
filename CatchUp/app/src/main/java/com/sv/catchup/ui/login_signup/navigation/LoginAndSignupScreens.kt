package com.sv.catchup.ui.login_signup.navigation

import com.sv.catchup.ui.home.navigation.HomeScreens

sealed class LoginAndSignupScreens(val route: String) {
    object GetStartedScreen: LoginAndSignupScreens("getStartedScreen")
    object LoginScreen : LoginAndSignupScreens("loginScreen")
    object SignupScreen : LoginAndSignupScreens("signupScreen")
}