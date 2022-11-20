package com.example.hashgenerator.ui.screens.result

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewModelScope
import com.example.hashgenerator.ui.screens.SharedViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ResultScreen(
    sharedViewModel: SharedViewModel,
) {

    val context = LocalContext.current
    val result by sharedViewModel.result
    val copiedTextHeight by sharedViewModel.copiedTextHeight

    Scaffold(
        topBar = { ResultAppBar() },
        content = {
            ResultContent(
                result = result,
                onCopyClicked = {
                    sharedViewModel.copyToClipBoard(result = result, localContext = context)
                    sharedViewModel.showCopiedText()
                },
                copiedTextHeight = copiedTextHeight
            )
        }
    )
}
