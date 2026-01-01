package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import mywaifump.composeapp.generated.resources.Res
import mywaifump.composeapp.generated.resources.balsamiq_sans_bold
import mywaifump.composeapp.generated.resources.balsamiq_sans_bolditalic
import mywaifump.composeapp.generated.resources.balsamiq_sans_italic
import mywaifump.composeapp.generated.resources.balsamiq_sans_regular
import org.jetbrains.compose.resources.Font

@Composable
fun fontFamily() = FontFamily(
    Font(
        resource = Res.font.balsamiq_sans_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resource = Res.font.balsamiq_sans_bold,
        weight = FontWeight.Bold
    ),
    Font(
        resource = Res.font.balsamiq_sans_italic,
        weight = FontWeight.Normal,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.balsamiq_sans_bolditalic,
        weight = FontWeight.Bold,
        style = FontStyle.Italic
    )
)

@Composable
fun typography() = Typography(
    titleSmall = TextStyle(
        fontFamily = fontFamily(),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    titleMedium = TextStyle(
        fontFamily = fontFamily(),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = fontFamily(),
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fontFamily(),
        fontSize = 12.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fontFamily(),
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = fontFamily(),
        fontSize = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fontFamily(),
        fontSize = 11.sp
    ),
    labelMedium = TextStyle(
        fontFamily = fontFamily(),
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = fontFamily(),
        fontSize = 14.sp
    )
)
