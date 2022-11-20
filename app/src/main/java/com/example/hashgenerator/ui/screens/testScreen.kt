package com.example.hashgenerator.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hashgenerator.R

@Composable
fun TestScreen() {
    var homeMainTextVisibility by rememberSaveable { mutableStateOf(true) }
    var dec by rememberSaveable { mutableStateOf(0f) }
    val alpha: Float by animateFloatAsState(
        targetValue = if (homeMainTextVisibility) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing,
        )
    )

    val angle: Float by animateFloatAsState(
        targetValue = if (homeMainTextVisibility) 1080f else 0f,
        animationSpec = tween(
            durationMillis = 800,
            easing = LinearEasing,
        )
    )
    AnimatedVisibility(
        visible = homeMainTextVisibility,
        exit = slideOutHorizontally(
            targetOffsetX = {
                it
            },
            animationSpec = tween(1100)
        )
    ) {
        Text(
            modifier = Modifier
                .padding(top = 120.dp, start = 60.dp, end = 60.dp)
                .rotate(angle)
                .alpha(alpha), text = stringResource(id = R.string.home_content_text),
            style = TextStyle(
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            textAlign = TextAlign.Center
        )
    }


    Button(onClick = {
        homeMainTextVisibility = !homeMainTextVisibility
        dec += 180f
    }) {
        Text(text = "AceCard")
    }
}
