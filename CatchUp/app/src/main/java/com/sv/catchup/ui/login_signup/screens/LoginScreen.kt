package com.sv.catchup.ui.login_signup.screens

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn.getClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.sv.catchup.MainActivity
import com.sv.catchup.R
import com.sv.catchup.data.loginReqBody
import com.sv.catchup.data.token
import com.sv.catchup.remote.CatchUpApi
import com.sv.catchup.remote.RetrofitInstance
import com.sv.catchup.ui.OAuthActivity
import com.sv.catchup.ui.components.GoogleSignInButton
import com.sv.catchup.ui.components.LoginButton
import com.sv.catchup.ui.home.HomeActivity
import com.sv.catchup.ui.login_signup.navigation.LoginAndSignupScreens
import com.sv.catchup.ui.theme.*
import com.sv.catchup.ui.videoCall.VideoCall
import com.sv.catchup.ui.videoCall.VideoConf
import com.sv.catchup.ui.videoCall.VideoGroup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginScreen(
    vm: loginViewModel,
    navController: NavController,
    context: Context
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgWhite)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = R.drawable.group_40), contentDescription ="logo",
            modifier = Modifier.height(25.dp),
            contentScale = ContentScale.FillHeight)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = username,
            label = { Text(text = "Username") },
            singleLine = true,
            onValueChange = { username = it },
            placeholder = { Text(text = "Username") },
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = darkNavy
            )
        )

        Spacer(modifier = Modifier.height(20.dp))
        //PASSWORD FIELD
        var passwordVisible by remember {
            mutableStateOf(false)
        }
        var password by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = darkNavy
            ),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = "toggle password visibility")
                }
            }
        )

        //LOGIN BUTTON
        Spacer(modifier = Modifier.height(30.dp))
        LoginButton(text = "LogIn",
            onClick = { login(context = context, username = username, password = password) })
        Spacer(modifier = Modifier.height(20.dp))
            GoogleSignInButton {context.startActivity(Intent(context,OAuthActivity::class.java))}

    }
        TextButton(onClick = {navController.navigate(LoginAndSignupScreens.SignupScreen.route)}){
            Text(text = "New to CatchUp? Create new Account",
            color = Color.Blue)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewLogin() {
    var navController: NavHostController? = null
    var context: Context? = null
    LoginScreen(loginViewModel(), navController!!, context!!)
}

private fun login(
    context: Context,
    username:String,
    password:String
){
    val newLogin = loginReqBody()
    newLogin.email=username
    newLogin.password=password
    val catchUpApi = RetrofitInstance.buildService(CatchUpApi::class.java)
    val requestCall = catchUpApi.login(newLogin)
    requestCall.enqueue(object :Callback<token>{
        override fun onResponse(call: Call<token>, response: Response<token>) {
            if(response.isSuccessful) {
                val tokenFile: SharedPreferences =
                    context.getSharedPreferences("tokenfile", Context.MODE_PRIVATE)
                val login: token? = response.body()
                tokenFile.edit().putString("token", login!!.token).apply()
                tokenFile.edit().putString("name", login!!.name).apply()
                context.startActivity(Intent(context,HomeActivity::class.java))
            }
            else Toast.makeText(context,"Login Failed", Toast.LENGTH_LONG).show()
        }

        override fun onFailure(call: Call<token>, t: Throwable) {
            Toast.makeText(context,"No response from server", Toast.LENGTH_LONG).show()        }
    })
}

