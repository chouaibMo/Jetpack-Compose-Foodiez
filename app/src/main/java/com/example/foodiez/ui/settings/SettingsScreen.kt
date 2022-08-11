package com.example.foodiez.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.foodiez.ui.theme.CreamWhite2
import com.example.foodiez.ui.theme.Dark
import com.example.foodiez.ui.theme.Gray

@Composable
fun SettingsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .background(CreamWhite2)
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
            painter = rememberImagePainter("https://www.menshairstylestoday.com/wp-content/uploads/2021/07/Messy-Hair.jpg"),
            contentDescription = "",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(1.5.dp, Dark, CircleShape)
                .clickable {
                    // bottom sheet select/take/remove photo ?
                }
        )
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "John Doe", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            Text(text = "175cm • 70kg", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray)
        }

    }
}

