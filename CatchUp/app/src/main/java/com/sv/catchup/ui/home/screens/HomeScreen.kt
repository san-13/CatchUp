package com.sv.catchup.ui.home.screens

import android.content.Context
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.sv.catchup.R
import com.sv.catchup.ui.home.homeViewModel
import kotlinx.coroutines.flow.collectLatest
import com.sv.catchup.ui.home.navigation.HomeScreens
import com.sv.catchup.ui.theme.BgWhite
import com.sv.catchup.ui.theme.ThemeRed
import com.sv.catchup.ui.videoCall.VideoCall
import com.sv.catchup.ui.videoCall.VideoGroup

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: homeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onNavigate: (String) -> Unit,
    context: Context
) {
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
                        text = "Katherine Langford",
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.abstract_user_flat_4),
                    contentDescription = "profile image",
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .size(70.dp)
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
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
        }
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Card(modifier = Modifier.size(height = 170.dp, width = 130.dp),
                    onClick = { navController.navigate(HomeScreens.EnterMeetingCodeScreen.route) }) {
                    Text(text = "Join")
                }
                Card(modifier = Modifier.size(height = 170.dp, width = 130.dp),
                    onClick = {context.startActivity(Intent(context,VideoGroup::class.java))}) {
                    Text(text = "Create")
                }
            }
        }
    }
}

@Composable
fun EnterMeetCodeScreen(
    navController: NavController
) {
    var meetingId by remember {
        mutableStateOf("")
    }
    Dialog(onDismissRequest = {navController.navigateUp()}) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = BgWhite,
            elevation = 16.dp,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedTextField(
                    value = meetingId,
                    onValueChange = { meetingId = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Enter Meeting Id")
                    },
                    singleLine = true,
                )
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(32.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Join")
                }
            }
        }
    }

}

@Composable
@Preview
fun PreviewHomeScreen() {

}