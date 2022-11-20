package com.example.hashgenerator.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hashgenerator.R
import com.example.hashgenerator.ui.theme.topAppBarBgColor
import com.example.hashgenerator.ui.theme.topAppBarContentColor

@Composable
fun HomeAppBar(
    onClearClicked: () -> Unit,
) {

    TopAppBar(
        title = { Text(text = stringResource(id = R.string.home_appbar_title)) },
        backgroundColor = MaterialTheme.colors.topAppBarBgColor,
        contentColor = MaterialTheme.colors.topAppBarContentColor,
        actions = {
            ClearAction(
                onClearClicked = onClearClicked
            )
        }
    )
}

@Composable
fun ClearAction(
    onClearClicked: () -> Unit,
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_menu),
            contentDescription = "",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onClearClicked()
                }) {
                Text(
                    modifier = Modifier.padding(end = 8.dp),
                    text = "Clear",
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}
