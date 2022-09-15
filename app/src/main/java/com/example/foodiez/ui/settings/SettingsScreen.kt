package com.example.foodiez.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.ExtraBold
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@Composable
fun SettingsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .background(colors.background)
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 30.dp)
    ) {
        SettingsHeader()
    }
}

@Composable
fun SettingsHeader() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Image(
            painter = rememberImagePainter("https://images.pexels.com/photos/2531553/pexels-photo-2531553.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
            contentDescription = "",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(3.dp, colors.onSurface, CircleShape)
                .clickable {
                    // bottom sheet select/take/remove photo ?
                }
        )
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "John Doe", fontSize = 24.sp, fontWeight = ExtraBold, color = colors.onBackground)
            Text(text = "175cm • 70kg", fontSize = 13.sp, fontWeight = SemiBold, color = Color.Gray)
        }

    }
}

