package com.example.hashgenerator.ui.screens.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import com.example.hashgenerator.R
import com.example.hashgenerator.ui.theme.customBlue100
import com.example.hashgenerator.utils.Constants.SUCCESS_ANIMATION_DURATION
import kotlinx.coroutines.delay

@Composable
fun SuccessScreen(
    navigateToResultScreen: () -> Unit,
    resetHomeScreenFields: () -> Unit,
    homeItemsVisibility: Boolean,
) {

    val successScreenAlpha: Float by animateFloatAsState(
        targetValue = if (homeItemsVisibility) 0f else 1f,
        animationSpec = tween(
            durationMillis = SUCCESS_ANIMATION_DURATION,
            easing = LinearEasing,
        )
    )
    LaunchedEffect(key1 = true ){
        delay(1500)
        navigateToResultScreen()
        delay(500)
        resetHomeScreenFields()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .alpha(successScreenAlpha)
            .background(customBlue100),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(painter = painterResource(id = R.drawable.ic_check_circle), contentDescription = "")
    }
}
