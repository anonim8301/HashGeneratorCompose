package com.example.hashgenerator.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hashgenerator.R
import com.example.hashgenerator.ui.theme.customBlue200
import com.example.hashgenerator.ui.theme.customWhite
import com.example.hashgenerator.ui.theme.homeContentBg
import com.example.hashgenerator.ui.theme.splashScreenBackgroundColor
import com.example.hashgenerator.utils.Constants.HOME_ANIMATION_DURATION

@Composable
fun HomeContent(
    homeContentTextEditField: String,
    onTextChanged: (String) -> Unit,
    onItemSelected: (String) -> Unit,
    homeItemsVisibility: Boolean,
    onGenerateClicked: () -> Unit,
    homeItemsAlpha: Float,
    currentPage: String,
    navigateToResultScreen: () -> Unit,
    homeButtonEnabled: Boolean,
    resetHomeScreenFields: () -> Unit,
    hashAlgorithm: String
) {
    Crossfade(
        targetState = currentPage,
        animationSpec = tween(3000)
    ) { screen ->
        when (screen) {
            "GenerateContent" -> GenerateContent(
                homeContentTextEditField = homeContentTextEditField,
                onTextChanged = onTextChanged,
                homeItemsVisibility = homeItemsVisibility,
                onGenerateClicked = onGenerateClicked,
                homeItemsAlpha = homeItemsAlpha,
                homeButtonEnabled = homeButtonEnabled,
                onItemSelected = onItemSelected,
                hashAlgorithm = hashAlgorithm
            )
            "SuccessScreen" -> SuccessScreen(
                homeItemsVisibility = homeItemsVisibility,
                navigateToResultScreen = navigateToResultScreen,
                resetHomeScreenFields = resetHomeScreenFields
            )
        }
    }
}

@Composable
fun GenerateContent(
    homeContentTextEditField: String,
    onItemSelected: (String) -> Unit,
    onTextChanged: (String) -> Unit,
    homeItemsVisibility: Boolean,
    onGenerateClicked: () -> Unit,
    homeItemsAlpha: Float,
    homeButtonEnabled: Boolean,
    hashAlgorithm: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.homeContentBg),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        MainText(
            homeItemsAlpha = homeItemsAlpha
        )
        ChooseCodeDropDown(
            onItemSelected = onItemSelected,
            homeItemsVisibility = homeItemsVisibility,
            homeItemsAlpha = homeItemsAlpha,
            hashAlgorithm = hashAlgorithm
        )
        TextInput(
            homeItemsAlpha = homeItemsAlpha,
            homeItemsVisibility = homeItemsVisibility,
            homeContentTextEditField = homeContentTextEditField,
            onTextChanged = onTextChanged,
        )

        Spacer(modifier = Modifier.weight(1f))
        GenerateButton(
            onGenerateClicked = onGenerateClicked,
            homeItemsAlpha = homeItemsAlpha,
            homeButtonEnabled = homeButtonEnabled
        )
    }
}


@Composable
fun MainText(
    homeItemsAlpha: Float,
) {

    Text(
        modifier = Modifier
            .padding(top = 120.dp, start = 60.dp, end = 60.dp)
            .alpha(homeItemsAlpha),
        text = stringResource(id = R.string.home_content_text),
        style = TextStyle(
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = customWhite
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun ChooseCodeDropDown(
    onItemSelected: (String) -> Unit,
    homeItemsVisibility: Boolean,
    homeItemsAlpha: Float,
    hashAlgorithm: String
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val angle: Float by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f
    )
    var parentSize by remember {
        mutableStateOf(IntSize.Zero)
    }
    val dropdownOptions = stringArrayResource(id = R.array.home_code_options)

    AnimatedVisibility(
        visible = homeItemsVisibility,
        exit = slideOutHorizontally(
            targetOffsetX = {
                it
            },
            animationSpec = tween(HOME_ANIMATION_DURATION)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(homeItemsAlpha)
                .padding(top = 32.dp, start = 16.dp, end = 16.dp)
                .onGloballyPositioned { parentSize = it.size }
                .height(60.dp)
                .background(MaterialTheme.colors.splashScreenBackgroundColor)
                .clickable { expanded = true },

            verticalAlignment = Alignment.CenterVertically,
        ) {

            Image(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                painter = painterResource(id = R.drawable.ic_lock),
                contentDescription = "",
                colorFilter = ColorFilter.tint(customBlue200)
            )
            Text(
                modifier = Modifier.weight(8f),
                text = hashAlgorithm,
                color = Color.White,
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Bold
            )
            IconButton(
                modifier = Modifier
                    .weight(1.5f)
                    .alpha(ContentAlpha.medium)
                    .rotate(degrees = angle),
                onClick = { expanded = true },
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "contentDescription",
                    tint = customBlue200
                )
            }
            DropdownMenu(
                modifier = Modifier
                    .width(with(LocalDensity.current) { parentSize.width.toDp() })
                    .background(customWhite),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

                dropdownOptions.forEach { item ->
                    DropdownMenuItem(
                        modifier = Modifier
                            .background(customWhite),
                        onClick = {
                            expanded = false
                            onItemSelected(item)
                        }
                    ) {
                        Text(color = Color.Black, text = item)
                    }
                }
            }
        }
    }
}

@Composable
fun TextInput(
    homeContentTextEditField: String,
    onTextChanged: (String) -> Unit,
    homeItemsVisibility: Boolean,
    homeItemsAlpha: Float,
) {

    AnimatedVisibility(
        visible = homeItemsVisibility,
        exit = slideOutHorizontally(
            targetOffsetX = {
                -it
            },
            animationSpec = tween(HOME_ANIMATION_DURATION)
        )
    ) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .alpha(homeItemsAlpha)
                .padding(all = 16.dp)
                .background(MaterialTheme.colors.splashScreenBackgroundColor),
            value = homeContentTextEditField,
            onValueChange = {
                onTextChanged(it)
            },
            colors = TextFieldDefaults.textFieldColors(textColor = customWhite),
            maxLines = 6
        )
    }
}

@Composable
fun GenerateButton(
    onGenerateClicked: () -> Unit,
    homeItemsAlpha: Float,
    homeButtonEnabled: Boolean,
) {

    Button(
        modifier = Modifier
            .alpha(homeItemsAlpha)
            .height(80.dp)
            .fillMaxWidth()
            .padding(all = 16.dp),
        onClick = { onGenerateClicked() },
        colors = ButtonDefaults.buttonColors(backgroundColor = customBlue200),
        enabled = homeButtonEnabled
    ) {
        Text(
            modifier = Modifier,
            color = customWhite,
            fontSize = 16.sp,
            text = "GENERATE"
        )
    }
}

