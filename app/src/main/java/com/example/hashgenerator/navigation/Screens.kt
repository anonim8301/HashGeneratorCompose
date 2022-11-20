package com.example.hashgenerator.navigation

import androidx.navigation.NavHostController
import com.example.hashgenerator.utils.Constants.HOME_SCREEN
import com.example.hashgenerator.utils.Constants.RESULT_SCREEN
import com.example.hashgenerator.utils.Constants.SPLASH_SCREEN

class Screens(navController: NavHostController) {

    val toHome: () -> Unit = {
        navController.navigate(HOME_SCREEN) {
            popUpTo(SPLASH_SCREEN) {
                inclusive = true
            }
        }
    }

    val toResult: () -> Unit = {
        navController.navigate(RESULT_SCREEN)
    }
}