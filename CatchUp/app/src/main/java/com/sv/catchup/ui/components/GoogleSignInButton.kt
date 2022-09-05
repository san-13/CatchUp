package com.sv.catchup.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sv.catchup.R

@Composable
fun GoogleSignInButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ),
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.google_icon),
                contentDescription = "google icon"
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(text = "Login/Sign in with Google", fontSize = 18.sp, maxLines = 1)
        }
    }
}