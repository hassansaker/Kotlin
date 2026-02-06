package com.sip.phoneclient.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * SIP Phone Dark Color Scheme
 * Optimized for low-light environments with good contrast
 */
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlueLight,           // Navigation, primary actions
    onPrimary = Color.Black,              // Text on primary
    primaryContainer = PrimaryBlue,       // Containers using primary
    onPrimaryContainer = Color.White,

    secondary = SecondaryGreenLight,      // Call/Answer actions
    onSecondary = Color.Black,
    secondaryContainer = SecondaryGreen,
    onSecondaryContainer = Color.White,

    error = ErrorRedLight,                // Decline/End call
    onError = Color.Black,
    errorContainer = ErrorRed,
    onErrorContainer = Color.White,

    background = SurfaceDark,             // Main background
    onBackground = OnSurfaceDark,         // Text on background
    surface = SurfaceDark,                // Cards, sheets
    onSurface = OnSurfaceDark,            // Text on surfaces
    surfaceVariant = SurfaceVariantDark,  // Elevated surfaces
    onSurfaceVariant = Color(0xFFCAC4D0),

    outline = DividerDark,                // Borders, dividers
    outlineVariant = Color(0xFF49454F)
)

/**
 * SIP Phone Light Color Scheme
 * Clean and professional for daytime use
 */
private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,                // Navigation, primary actions
    onPrimary = Color.White,              // Text on primary
    primaryContainer = Color(0xFFBBDEFB), // Light blue containers
    onPrimaryContainer = Color(0xFF001D36),

    secondary = SecondaryGreen,           // Call/Answer actions
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFC8E6C9), // Light green containers
    onSecondaryContainer = Color(0xFF002106),

    error = ErrorRed,                     // Decline/End call
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),   // Light red containers
    onErrorContainer = Color(0xFF410002),

    background = SurfaceLight,            // Main background
    onBackground = OnSurfaceLight,        // Text on background
    surface = SurfaceLight,               // Cards, sheets
    onSurface = OnSurfaceLight,           // Text on surfaces
    surfaceVariant = SurfaceVariantLight, // Elevated surfaces
    onSurfaceVariant = Color(0xFF49454F),

    outline = DividerLight,               // Borders, dividers
    outlineVariant = Color(0xFFCAC4D0)
)

/**
 * SIP Phone Theme
 * Material 3 theme with custom color scheme for SIP client
 *
 * @param darkTheme Whether to use dark theme (default: follows system)
 * @param dynamicColor Whether to use dynamic colors on Android 12+ (default: false to maintain branding)
 */
@Composable
fun SIPPhoneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color disabled by default to maintain consistent SIP Phone branding
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

/**
 * Legacy theme name for backward compatibility during migration
 * @deprecated Use SIPPhoneTheme instead
 */
@Deprecated("Use SIPPhoneTheme instead", ReplaceWith("SIPPhoneTheme(darkTheme, dynamicColor, content)"))
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    SIPPhoneTheme(darkTheme, dynamicColor, content)
}