package com.yandex.bugs.sample.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Светлая тема Яндекса
private val YandexLightColorScheme = lightColorScheme(
    primary = YandexRedLight,
    onPrimary = Color.White,
    primaryContainer = YandexRedLight.copy(alpha = 0.8f),
    onPrimaryContainer = Color.White,
    secondary = YandexYellowLight,
    onSecondary = YandexTextPrimaryLight,
    secondaryContainer = YandexYellowLight.copy(alpha = 0.7f),
    onSecondaryContainer = YandexTextPrimaryLight,
    tertiary = YandexBlueLight,
    onTertiary = Color.White,
    background = YandexBackgroundLight,
    onBackground = YandexTextPrimaryLight,
    surface = YandexSurfaceLight,
    onSurface = YandexTextPrimaryLight,
    surfaceVariant = YandexBackgroundLight.copy(alpha = 0.7f),
    onSurfaceVariant = YandexTextSecondaryLight,
    error = Color(0xFFBA1B1B)
)

// Темная тема Яндекса
private val YandexDarkColorScheme = darkColorScheme(
    primary = YandexRedDark,
    onPrimary = Color.White,
    primaryContainer = YandexRedDark.copy(alpha = 0.8f),
    onPrimaryContainer = Color.White,
    secondary = YandexYellowDark,
    onSecondary = YandexTextPrimaryDark,
    secondaryContainer = YandexYellowDark.copy(alpha = 0.7f),
    onSecondaryContainer = YandexTextPrimaryDark,
    tertiary = YandexBlueDark,
    onTertiary = Color.White,
    background = YandexBackgroundDark,
    onBackground = YandexTextPrimaryDark,
    surface = YandexSurfaceDark,
    onSurface = YandexTextPrimaryDark,
    surfaceVariant = YandexBackgroundDark.copy(alpha = 0.6f),
    onSurfaceVariant = YandexTextSecondaryDark,
    error = Color(0xFFFFB4A9)
)

@Composable
fun BugsSampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> YandexDarkColorScheme
        else -> YandexLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}