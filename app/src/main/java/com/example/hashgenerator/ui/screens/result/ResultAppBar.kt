package com.example.hashgenerator.ui.screens.result

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.hashgenerator.R
import com.example.hashgenerator.ui.theme.topAppBarBgColor
import com.example.hashgenerator.ui.theme.topAppBarContentColor

@Composable
fun ResultAppBar() {

    TopAppBar(
        title = { Text(text = stringResource(id = R.string.result_appbar_title)) },
        backgroundColor = MaterialTheme.colors.topAppBarBgColor,
        contentColor = MaterialTheme.colors.topAppBarContentColor
    )
}
