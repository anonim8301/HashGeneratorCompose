package com.example.hashgenerator.navigation.destinations


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import com.example.hashgenerator.ui.screens.SharedViewModel
import com.example.hashgenerator.ui.screens.home.HomeScreen
import com.example.hashgenerator.utils.Constants.HOME_SCREEN
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
fun NavGraphBuilder.homeComposable(
    navigateToResultScreen: () -> Unit,
    sharedViewModel: SharedViewModel,
) {

    composable(
        route = HOME_SCREEN,
        exitTransition = {
            slideOutVertically(
                targetOffsetY = { -it },
                animationSpec = tween(600)
            )
        },
        popEnterTransition = {
            slideInVertically(
                initialOffsetY = { -it },
                animationSpec = tween(600)
            )
        }
    ) {
        HomeScreen(
            navigateToResultScreen = navigateToResultScreen,
            sharedViewModel = sharedViewModel
        )
    }
}