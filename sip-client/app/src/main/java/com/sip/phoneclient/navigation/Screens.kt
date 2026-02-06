package com.sip.phoneclient.navigation

/**
 * Sealed class representing all navigation destinations in the SIP Phone app
 */
sealed class Screen(val route: String) {
    /**
     * Dialer screen - Main tab for making calls
     * Shows number pad and call button
     */
    data object Dialer : Screen("dialer")

    /**
     * Contacts screen - Browse and search contacts
     * Shows favorites and alphabetical contact list
     */
    data object Contacts : Screen("contacts")

    /**
     * Recents screen - Call history
     * Shows recent, missed, and outgoing calls
     */
    data object Recents : Screen("recents")

    /**
     * Settings screen - App configuration
     * Shows SIP account, audio, network, and appearance settings
     */
    data object Settings : Screen("settings")
}

/**
 * List of all bottom navigation items in display order
 */
val bottomNavItems = listOf(
    Screen.Dialer,
    Screen.Contacts,
    Screen.Recents,
    Screen.Settings
)
