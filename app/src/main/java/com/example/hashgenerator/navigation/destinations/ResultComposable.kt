package com.example.hashgenerator.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import com.example.hashgenerator.ui.screens.SharedViewModel
import com.example.hashgenerator.ui.screens.result.ResultScreen
import com.example.hashgenerator.utils.Constants.RESULT_SCREEN
import com.google.accompanist.navigation.animation.composable


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.resultComposable(
    sharedViewModel: SharedViewModel
) {

    composable(
        route = RESULT_SCREEN,
        exitTransition = {
            slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(600)
            )
        },
        enterTransition = {
            slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(600)
            )
        }
    ) {
        ResultScreen(sharedViewModel = sharedViewModel)
    }
}