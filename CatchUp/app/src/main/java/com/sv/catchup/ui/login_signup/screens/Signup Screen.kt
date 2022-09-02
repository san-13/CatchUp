package com.sv.catchup.ui.login_signup.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.sv.catchup.R
import com.sv.catchup.ui.components.LoginButton
import com.sv.catchup.ui.home.HomeActivity
import com.sv.catchup.ui.login_signup.navigation.LoginAndSignupScreens
import com.sv.catchup.ui.theme.BgWhite
import com.sv.catchup.ui.theme.TextFieldGrey
import com.sv.catchup.ui.theme.ThemeRed

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SignupScreen(
    navController: NavController,
    context: Context
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgWhite)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Create Account",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(50.dp))
        //Name Field
        var name by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = TextFieldGrey,
                focusedBorderColor = ThemeRed,
                focusedLabelColor = ThemeRed
            ),
            singleLine = true,
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Name") },
            textStyle = TextStyle(fontSize = 16.sp),

            )
        Spacer(modifier = Modifier.height(10.dp))
        //Phone Field
        var phone by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = TextFieldGrey,
                focusedBorderColor = ThemeRed,
                focusedLabelColor = ThemeRed
            ),
            singleLine = true,
            value = phone,
            onValueChange = { phone = it },
            label = { Text(text = "Phone") },
            textStyle = TextStyle(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.height(10.dp))
        //Email Field
        var email by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = TextFieldGrey,
                focusedBorderColor = ThemeRed,
                focusedLabelColor = ThemeRed
            ),
            singleLine = true,
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            textStyle = TextStyle(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.height(10.dp))
        //Password Field
        var password by remember {
            mutableStateOf("")
        }
        var passwordVisible by remember {
            mutableStateOf(false)
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = TextFieldGrey,
                focusedBorderColor = ThemeRed,
                focusedLabelColor = ThemeRed
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
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
        Spacer(modifier = Modifier.height(50.dp))
        //Signup Button
        LoginButton(text = "Sign Up",
            onClick = { context.startActivity(Intent(context, HomeActivity::class.java)) })
        Spacer(modifier = Modifier.height(20.dp))
        //SignUpWithGoogle
        Card(
            onClick = { },
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
        Spacer(modifier = Modifier.height(30.dp))
        TextButton(onClick = { navController.navigate(LoginAndSignupScreens.LoginScreen.route) }) {
            Text(
                text = "Already have an account? Login",
                fontSize = 14.sp,
                color = Color.Blue
            )
        }
    }
}