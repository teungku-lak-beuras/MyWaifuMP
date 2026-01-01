package com.example.compose

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
actual fun provideColorScheme(
    darkTheme: Boolean,
    dynamicColor: Boolean
): ColorScheme {
    return if (darkTheme) darkScheme else lightScheme
}
