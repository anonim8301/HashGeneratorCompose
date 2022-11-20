package com.example.hashgenerator.ui.screens.home

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.hashgenerator.ui.screens.SharedViewModel
import com.example.hashgenerator.ui.theme.topAppBarBgColor
import com.example.hashgenerator.utils.Constants.HOME_ANIMATION_DURATION
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    sharedViewModel: SharedViewModel,
    navigateToResultScreen: () -> Unit,
) {
    val context = LocalContext.current
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colors.topAppBarBgColor
    )
    val homeContentTextEditField by sharedViewModel.textToHash
    val homeItemsVisibility by sharedViewModel.homeItemsVisibility
    val homeButtonEnabled by sharedViewModel.homeButtonEnabled
    val currentScreen by sharedViewModel.currentScreen
    val hashAlgorithm by sharedViewModel.hashAlgorithm

    val homeItemsAlpha: Float by animateFloatAsState(
        targetValue = if (homeItemsVisibility) 1f else 0f,
        animationSpec = tween(
            durationMillis = HOME_ANIMATION_DURATION,
            easing = LinearEasing,
        )
    )



    Scaffold(
        topBar = {
            HomeAppBar(onClearClicked = {
                sharedViewModel.setTextToTextToHash("")
            })
        },
        content = {
            HomeContent(
                homeItemsAlpha = homeItemsAlpha,
                homeContentTextEditField = homeContentTextEditField,
                onTextChanged = { sharedViewModel.setTextToTextToHash(it) },
                homeItemsVisibility = homeItemsVisibility,
                currentPage = currentScreen,
                navigateToResultScreen = navigateToResultScreen,
                homeButtonEnabled = homeButtonEnabled,
                hashAlgorithm = hashAlgorithm,
                onGenerateClicked = {
                    if (homeContentTextEditField.isNotEmpty()) {
                        sharedViewModel.getHash()
                        sharedViewModel.changeFields(
                            visible = false,
                            screen = "SuccessScreen",
                            buttonEnabled = false
                        )
                    } else makeToast(context)
                },
                resetHomeScreenFields = {
                    sharedViewModel.changeFields(
                        visible = true,
                        screen = "GenerateContent",
                        buttonEnabled = true
                    )
                    sharedViewModel.setTextToTextToHash("")
                },
                onItemSelected = {
                    sharedViewModel.changeHashAlgorithm(it)
                }
            )
        }
    )
}

fun makeToast(context: Context) {
    Toast.makeText(
        context,
        "Empty field!",
        Toast.LENGTH_SHORT
    ).show()
}
