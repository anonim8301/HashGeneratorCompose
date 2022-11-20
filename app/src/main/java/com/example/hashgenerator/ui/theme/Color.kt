package com.example.hashgenerator.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF141414)

val customBlue100 = Color(0xFF33e0c4)
val customBlue200 = Color(0xFF1165cc)
val customBlue300 = Color(0xFF16255F)
val customBlue400 = Color(0xFF040f2d)
val customBlue500 = Color(0xFF050926)

val customWhite = Color(0xFFE4E4E9)

val Colors.splashScreenBackgroundColor: Color
    @Composable
    get() = customBlue300

val Colors.topAppBarContentColor: Color
    @Composable
    get() = customWhite

val Colors.topAppBarBgColor: Color
    @Composable
    get() = customBlue500

val Colors.homeContentBg: Color
    @Composable
    get() = customBlue400