package com.example.foodiez.ui.home

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodiez.domain.statistic.Statistic
import com.example.foodiez.ui.theme.*
import com.hitanshudhawan.circularprogressbar.CircularProgressBar

@Composable
fun MacroNutrimentsCard(stats: Statistic) {
    val carbs: Float by animateFloatAsState(
        targetValue = stats.totalCarbs.toFloat(),
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )
    val fats: Float by animateFloatAsState(
        targetValue = stats.totalFats.toFloat(),
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )
    val proteins: Float by animateFloatAsState(
        targetValue = stats.totalProteins.toFloat(),
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        elevation = 5.dp,
        backgroundColor = Gray
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp)
        ) {

            Column(Modifier.padding(8.dp)) {
                Text(text = "Carbs", color = Color.DarkGray, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                Text(text = "${stats.totalCarbs}/300 g (90%)", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Green100)
                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Proteins", color = Color.DarkGray, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                Text(text = "${stats.totalProteins}/160 g (90%)", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Blue100)
                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Fats", color = Color.DarkGray, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                Text(text = "${stats.totalFats}/70 g (85%)", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Orange100)

            }
            // TODO : remove hardcoded max progress
            // TODO : retrieve preferences
            Box(contentAlignment = Alignment.Center) {
                CircularProgressBar(
                    modifier = Modifier.size(145.dp),
                    progress = carbs,
                    progressMax = 300f,
                    progressBarColor = Green100,
                    progressBarWidth = 13.dp,
                    backgroundProgressBarColor = Green50,
                    backgroundProgressBarWidth = 13.dp,
                    roundBorder = true,
                    startAngle = 0f
                )

                CircularProgressBar(
                    modifier = Modifier.size(115.dp),
                    progress = proteins,
                    progressMax = 160f,
                    progressBarColor = Blue100,
                    progressBarWidth = 13.dp,
                    backgroundProgressBarColor = Blue50,
                    backgroundProgressBarWidth = 13.dp,
                    roundBorder = true,
                    startAngle = 0f
                )

                CircularProgressBar(
                    modifier = Modifier.size(85.dp),
                    progress = fats,
                    progressMax = 70f,
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