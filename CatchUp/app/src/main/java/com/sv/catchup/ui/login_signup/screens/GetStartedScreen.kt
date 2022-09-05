package com.sv.catchup.ui.login_signup.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sv.catchup.R
import com.sv.catchup.ui.login_signup.navigation.LoginAndSignupScreens
import com.sv.catchup.ui.theme.LightBlue
import com.sv.catchup.ui.theme.ThemeRed

@Composable
fun GetStartedScreen(
    navController: NavController
){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(LightBlue)
        .padding(32.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly) {
            Image(modifier = Modifier.height(400.dp),
                painter = painterResource(id = R.drawable.app_display),
                contentDescription ="app_display",
            contentScale = ContentScale.FillHeight)
            Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text ="Welcome to CatchUp",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Yellow)
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "An easy and efficient video calling hackathon to connect with people",
                color = Color.White,
                textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(30.dp))
                Button(onClick = { navController.navigate(LoginAndSignupScreens.LoginScreen.route) },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ThemeRed
                ),
                modifier = Modifier.size(width = 150.dp, height = 50.dp)) {
                    Text(text = "Get Started",
                    color = Color.White)
                }
            }
    }
}