package com.sv.catchup.ui.login_signup.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sv.catchup.ui.login_signup.viewmodel.loginViewModel
import com.sv.catchup.ui.theme.BgWhite
import com.sv.catchup.ui.theme.TextFieldGrey
import com.sv.catchup.ui.theme.TextGrey
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.sv.catchup.R
import com.sv.catchup.ui.components.LoginButton

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginScreen(vm: loginViewModel) {
    val s: String = ""
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgWhite)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(250.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Catch Up!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Login Now",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Enter your details to continue",
                fontSize = 18.sp,
                color = TextGrey
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        //USERNAME FIELD
        var username by remember {
            mutableStateOf("")
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(TextFieldGrey),
            shape = RoundedCornerShape(8.dp),
            elevation = 0.dp
        ) {
            TextField(
                value = username,
                onValueChange = { username = it },
                placeholder = { Text(text = "Username") },
                textStyle = TextStyle(fontSize = 16.sp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        //PASSWORD FIELD
        var passwordVisible by remember {
            mutableStateOf(false)
        }
        var password by remember {
            mutableStateOf("")
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(TextFieldGrey),
            shape = RoundedCornerShape(8.dp),
            elevation = 0.dp
        ) {
            TextField(value = password,
                onValueChange = { password = it },
                placeholder = { Text(text = "Password") },
                textStyle = TextStyle(fontSize = 16.sp),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = "toggle password visibility")
                    }
                }
            )
        }
        //LOGIN BUTTON
        Spacer(modifier = Modifier.height(30.dp))
        LoginButton(text = "LogIn")
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .size(height = 50.dp, width = 180.dp)
                .background(
                    Color.Transparent
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.btn_google_signin_light_normal_web),
                contentDescription = "login via google button",
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewLogin() {
    LoginScreen(loginViewModel())
}