package com.sv.catchup.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sv.catchup.ui.theme.SatThemeRed
import com.sv.catchup.ui.theme.ThemeRed

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = SatThemeRed
        ),
        shape = RoundedCornerShape(24.dp)
    ) {

        Text(
            text = text,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

    }
}

@Composable
@Preview
fun PreviewButton() {
    LoginButton(text = "Login", {})
}