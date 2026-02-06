package com.sip.phoneclient.ui.theme

import androidx.compose.ui.graphics.Color

// Light Theme Colors
val PrimaryBlue = Color(0xFF1976D2)      // Material Blue 700 - Navigation
val PrimaryBlueLight = Color(0xFF64B5F6) // Lighter blue for dark mode
val SecondaryGreen = Color(0xFF4CAF50)   // Success/Call actions
val SecondaryGreenLight = Color(0xFF81C784) // Lighter green for dark mode
val ErrorRed = Color(0xFFE53935)         // Decline/End call actions
val ErrorRedLight = Color(0xFFE57373)    // Lighter red for dark mode

// Surface Colors - Light Mode
val SurfaceLight = Color(0xFFFAFAFA)     // Light background
val OnSurfaceLight = Color(0xFF1C1B1F)   // Text on light surfaces
val SurfaceVariantLight = Color(0xFFE7E0EC) // Cards, elevated surfaces

// Surface Colors - Dark Mode
val SurfaceDark = Color(0xFF1C1B1F)      // Dark background
val OnSurfaceDark = Color(0xFFE6E1E5)    // Text on dark surfaces
val SurfaceVariantDark = Color(0xFF49454F) // Cards, elevated surfaces

// Additional UI Colors
val DialerButtonLight = Color(0xFFF5F5F5) // Keypad button background light
val DialerButtonDark = Color(0xFF2C2C2C)  // Keypad button background dark
val DividerLight = Color(0xFFE0E0E0)     // Dividers in light mode
val DividerDark = Color(0xFF3C3C3C)      // Dividers in dark mode

// Call Quality Indicators
val QualityGood = Color(0xFF4CAF50)      // Good connection
val QualityMedium = Color(0xFFFFA726)    // Medium connection
val QualityPoor = Color(0xFFEF5350)      // Poor connection

// Legacy colors (kept for backward compatibility during migration)
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)