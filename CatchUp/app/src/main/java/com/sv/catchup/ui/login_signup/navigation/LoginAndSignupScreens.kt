package com.sv.catchup.ui.login_signup.navigation

sealed class LoginAndSignupScreens(val route: String) {
    object LoginScreen : LoginAndSignupScreens("loginScreen")
    object SignupScreen : LoginAndSignupScreens("signupScreen")
}