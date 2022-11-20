package com.example.hashgenerator.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.hashgenerator.navigation.destinations.homeComposable
import com.example.hashgenerator.navigation.destinations.resultComposable
import com.example.hashgenerator.navigation.destinations.splashComposable
import com.example.hashgenerator.ui.screens.SharedViewModel
import com.example.hashgenerator.utils.Constants.SPLASH_SCREEN
import com.google.accompanist.navigation.animation.AnimatedNavHost

@ExperimentalAnimationApi
@Composable
fun SetupNavigation(
    sharedViewModel: SharedViewModel,
    navController: NavHostController,
) {

    val screens = remember(navController) {
        Screens(navController = navController)
    }

    AnimatedNavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        splashComposable(
            navigateToHome = screens.toHome
        )
        homeComposable(
            sharedViewModel = sharedViewModel,
            navigateToResultScreen = screens.toResult
        )
        resultComposable(
            sharedViewModel = sharedViewModel
        )
    }
}