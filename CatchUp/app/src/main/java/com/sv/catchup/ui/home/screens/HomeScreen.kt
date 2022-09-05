package com.sv.catchup.ui.home.screens

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.sv.catchup.MainActivity
import com.sv.catchup.R
import com.sv.catchup.data.channelCredential
import com.sv.catchup.data.channelToken
import com.sv.catchup.data.logout
import com.sv.catchup.data.token
import com.sv.catchup.remote.CatchUpApi
import com.sv.catchup.remote.RetrofitInstance
import com.sv.catchup.ui.home.HomeActivity
import com.sv.catchup.ui.home.homeViewModel
import kotlinx.coroutines.flow.collectLatest
import com.sv.catchup.ui.home.navigation.HomeScreens
import com.sv.catchup.ui.login_signup.loginandsignup
import com.sv.catchup.ui.theme.BgWhite
import com.sv.catchup.ui.theme.JoinTeal
import com.sv.catchup.ui.theme.ThemeRed
import com.sv.catchup.ui.videoCall.VideoCall
import com.sv.catchup.ui.videoCall.VideoGroup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: homeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onNavigate: (String) -> Unit,
    context: Context
) {
    var tokenFile=context.getSharedPreferences("tokenfile", Context.MODE_PRIVATE)
    val default = context.resources.getString(R.string.app_name)
    val text = tokenFile?.getString("name",default).toString()
    val openDialog = remember {
        mutableStateOf(false)
    }
    val logoutPressed = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.hackothon_2), contentDescription = "logo",
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    Text(
                        text = "hey",
                        fontSize = 20.sp
                    )
                    Text(
                        text = text,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )
                }
                Column {
                    IconButton(onClick = { logoutPressed.value = true }) {
                        Image(
                            painter = painterResource(id = R.drawable.abstract_user_flat_4),
                            contentDescription = "profile image",
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .size(70.dp)
                        )
                    }
                    DropdownMenu(
                        expanded = logoutPressed.value,
                        onDismissRequest = { logoutPressed.value = false }) {
                        DropdownMenuItem(
                            onClick = { Logout(context) },
                            modifier = Modifier.background(Color.White)
                        ) {
                            Text(text = "Logout")
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ill),
                contentDescription = "illustration",
                modifier = Modifier.size(150.dp)
            )
        }
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                onClick = { //navController.navigate(HomeScreens.EnterMeetingCodeScreen.route)
                    openDialog.value = true
                },
                shape = RoundedCornerShape(16.dp),
                backgroundColor = JoinTeal
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "Join",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                onClick = { CreateChannel(context) },
                shape = RoundedCornerShape(16.dp),
                backgroundColor = ThemeRed
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Create",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

        }
    }
    if (openDialog.value) {
        var meetingId by remember {
            mutableStateOf("")
        }
        var progressBar = remember {
            mutableStateOf(false)
        }
        var meetingIdEmpty = remember {
            mutableStateOf(false)
        }
        Dialog(onDismissRequest = { openDialog.value = false }) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = BgWhite,
                elevation = 16.dp,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .padding(horizontal = 32.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(
                        value = meetingId,
                        onValueChange = { meetingId = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Meeting Id") },
                        placeholder = {
                            Text(text = "Enter Meeting Id")
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = JoinTeal,
                            focusedLabelColor = JoinTeal,
                            errorBorderColor = Color.Red
                        ),
                        isError = meetingIdEmpty.value
                    )
                    Column() {
                        Button(
                            onClick = {
                                if (meetingId == "") meetingIdEmpty.value = true
                                else {
                                    progressBar.value = true
                                    JoinChannel(context = context, channelName = meetingId)
                                }
                            },
                            shape = RoundedCornerShape(32.dp),
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(backgroundColor = JoinTeal)
                        ) {
                            Text(text = "Join")
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        if (progressBar.value) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}

fun CreateChannel(context: Context) {
    val catchUpApi = RetrofitInstance.buildService(CatchUpApi::class.java)
    val requestCall = catchUpApi.createChannel()
    requestCall.enqueue(object : Callback<channelCredential> {
        override fun onResponse(
            call: Call<channelCredential>,
            response: Response<channelCredential>
        ) {
            if (response.isSuccessful) {
                val channelCredential: channelCredential? = response.body()
                val intent = Intent(context, VideoGroup::class.java)
                intent.putExtra("channelName", channelCredential?.channelName)
                intent.putExtra("token", channelCredential?.token)
                context.startActivity(intent)
                //Toast.makeText(context,channelCredential?.channelName + " " + channelCredential?.token,Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Response Failure", Toast.LENGTH_LONG).show()
            }
        }
        override fun onFailure(call: Call<channelCredential>, t: Throwable) {
            Toast.makeText(context, "Call Failure", Toast.LENGTH_LONG).show()
        }

    })
}

fun JoinChannel(context: Context, channelName: String) {
    val catchUpApi = RetrofitInstance.buildService(CatchUpApi::class.java)
    val requestCall = catchUpApi.verifyChannel(channelName)
    requestCall.enqueue(object : Callback<channelToken> {
        override fun onResponse(call: Call<channelToken>, response: Response<channelToken>) {
            if (response.isSuccessful) {
                val channelToken: channelToken? = response.body()
                val intent = Intent(context, VideoGroup::class.java)
                intent.putExtra("channelName", channelName)
                intent.putExtra("token", channelToken?.token)
                context.startActivity(intent)
            } else {
                Toast.makeText(context, "Response Failure", Toast.LENGTH_LONG).show()
            }
        }
        override fun onFailure(call: Call<channelToken>, t: Throwable) {
            Toast.makeText(context, "Call Failure", Toast.LENGTH_LONG).show()
        }
    })
}

fun Logout(context: Context){
    var tokenFile=context.getSharedPreferences("tokenfile", Context.MODE_PRIVATE)
    val default = context.resources.getString(R.string.app_name)
    val text = tokenFile?.getString("token",default).toString()
    var token = "Bearer_$text"
    val catchUpApi = RetrofitInstance.buildService(CatchUpApi::class.java)
    val requestCall = catchUpApi.logout(token)
    requestCall.enqueue(object :Callback<logout>{
        override fun onResponse(call: Call<logout>, response: Response<logout>) {
            tokenFile!!.edit().putString("token",default).apply()
            context.startActivity(Intent(context, loginandsignup::class.java))
         /*   if(response.isSuccessful) {
                context.startActivity(Intent(context, loginandsignup::class.java))
            }
            else Toast.makeText(context,"Logout Failed", Toast.LENGTH_LONG).show()*/
        }

        override fun onFailure(call: Call<logout>, t: Throwable) {
           // Toast.makeText(context,"No response from server", Toast.LENGTH_LONG).show()
        }
    })
}