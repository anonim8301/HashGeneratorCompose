package com.example.hashgenerator.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder

import com.example.hashgenerator.ui.screens.SplashScreen
import com.example.hashgenerator.utils.Constants.SPLASH_SCREEN
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.splashComposable(
    navigateToHome: () -> Unit,
) {

    composable(
        route = SPLASH_SCREEN
    ) {
        SplashScreen(
            navigateToHome = navigateToHome
        )
    }
}