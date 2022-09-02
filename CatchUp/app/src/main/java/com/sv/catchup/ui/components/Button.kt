package com.sv.catchup.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sv.catchup.ui.theme.ThemeRed

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginButton(text: String,
onClick:()->Unit) {
    Card(modifier = Modifier.size(height = 50.dp, width = 180.dp),
    onClick = onClick) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ThemeRed),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
@Preview
fun PreviewButton() {
    LoginButton(text = "Login",{})
}