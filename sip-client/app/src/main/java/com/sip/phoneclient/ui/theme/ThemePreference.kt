package com.sip.phoneclient.ui.theme

import androidx.compose.runtime.compositionLocalOf

enum class ThemeMode {
    SYSTEM,
    LIGHT,
    DARK
}

/**
 * CompositionLocal to provide the current theme mode and a setter throughout the app
 */
val LocalThemeMode = compositionLocalOf { ThemeMode.SYSTEM }
