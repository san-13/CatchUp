package com.sv.catchup.ui.login_signup.screens

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
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
import com.sv.catchup.data.loginReqBody
import com.sv.catchup.data.signupReqBody
import com.sv.catchup.data.token
import com.sv.catchup.remote.CatchUpApi
import com.sv.catchup.remote.RetrofitInstance
import com.sv.catchup.ui.components.GoogleSignInButton
import com.sv.catchup.ui.components.LoginButton
import com.sv.catchup.ui.home.HomeActivity
import com.sv.catchup.ui.login_signup.navigation.LoginAndSignupScreens
import com.sv.catchup.ui.theme.BgWhite
import com.sv.catchup.ui.theme.TextFieldGrey
import com.sv.catchup.ui.theme.ThemeRed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.jar.Attributes

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
            label = { Text(text = "Username") },
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
            label = { Text(text = "Name") },
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
            onClick = { signup(context = context, name = name, email = email, password = password, phoneNumber = phone) })
        Spacer(modifier = Modifier.height(20.dp))
        //SignUpWithGoogle
        GoogleSignInButton {}
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

fun signup(
    context: Context,
    name:String,
    email:String,
    password:String,
    phoneNumber:String
){
    val newSignup = signupReqBody()
    newSignup.username=name
    newSignup.email=email
    newSignup.phoneNumber=phoneNumber
    newSignup.password=password
    val catchUpApi = RetrofitInstance.buildService(CatchUpApi::class.java)
    val requestCall = catchUpApi.signup(newSignup)
    requestCall.enqueue(object : Callback<token> {
        override fun onResponse(call: Call<token>, response: Response<token>) {
            if(response.isSuccessful) {
                val tokenFile: SharedPreferences =
                    context.getSharedPreferences("tokenfile", Context.MODE_PRIVATE)
                val login: token? = response.body()
                tokenFile.edit().putString("token", login!!.token).apply()
                context.startActivity(Intent(context,HomeActivity::class.java))
            }
            else Toast.makeText(context,"Sign in Failed", Toast.LENGTH_LONG).show()
        }
        override fun onFailure(call: Call<token>, t: Throwable) {
            Toast.makeText(context,"No response from server", Toast.LENGTH_LONG).show()
        }
    })
}