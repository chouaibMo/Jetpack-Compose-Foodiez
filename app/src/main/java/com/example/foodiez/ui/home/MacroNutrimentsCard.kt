package com.example.foodiez.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodiez.ui.theme.*
import com.hitanshudhawan.circularprogressbar.CircularProgressBar

@Composable
fun MacroNutrimentsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp,
        backgroundColor = Gray
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp)
        ) {

            Column(Modifier.padding(8.dp)) {
                Text(text = "Carbs", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                Text(text = "180/200 g (90%)", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Green100)
                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Proteins", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                Text(text = "90/100 g (90%)", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Blue100)
                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Fats", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                Text(text = "60/70 g (85%)", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Orange100)

            }

            Box(contentAlignment = Alignment.Center) {
                CircularProgressBar(
                    modifier = Modifier.size(145.dp),
                    progress = 35f,
                    progressMax = 100f,
                    progressBarColor = Green100,
                    progressBarWidth = 13.dp,
                    backgroundProgressBarColor = Green50,
                    backgroundProgressBarWidth = 13.dp,
                    roundBorder = true,
                    startAngle = 0f
                )

                CircularProgressBar(
                    modifier = Modifier.size(115.dp),
                    progress = 65f,
                    progressMax = 100f,
                    progressBarColor = Blue100,
                    progressBarWidth = 13.dp,
                    backgroundProgressBarColor = Blue50,
                    backgroundProgressBarWidth = 13.dp,
                    roundBorder = true,
                    startAngle = 0f
                )

                CircularProgressBar(
                    modifier = Modifier.size(85.dp),
                    progress = 80f,
                    progressMax = 100f,
                    progressBarColor = Orange100,
                    progressBarWidth = 13.dp,
                    backgroundProgressBarColor = Orange50,
                    backgroundProgressBarWidth = 13.dp,
                    roundBorder = true,
                    startAngle = 0f
                )
            }
        }

    }
}