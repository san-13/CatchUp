package com.sv.catchup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sv.catchup.ui.home.HomeActivity
import com.sv.catchup.ui.login_signup.loginandsignup
import com.sv.catchup.ui.theme.CatchUpTheme

class MainActivity : ComponentActivity() {
    private val SPLASH_TIME: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatchUpTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SplashScreen()
                }
            }
        }
        Handler().postDelayed({
            var tokenFile=getSharedPreferences("tokenfile", Context.MODE_PRIVATE)
            val default = resources.getString(R.string.app_name)
            val text = tokenFile?.getString("token",default).toString()
            if(text!=default) startActivity(Intent(this,HomeActivity::class.java))
            else startActivity(Intent(this, loginandsignup::class.java))
            finish()
        }, SPLASH_TIME)
    }
}

@Composable
fun SplashScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(painter = painterResource(id = R.drawable.catch_up), contentDescription ="logo",
            modifier = Modifier.height(25.dp),
        contentScale = ContentScale.FillHeight)}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CatchUpTheme {
        SplashScreen()
    }
}