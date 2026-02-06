package com.sip.phoneclient.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sip.phoneclient.ui.screens.contacts.ContactsScreen
import com.sip.phoneclient.ui.screens.dialer.DialerScreen
import com.sip.phoneclient.ui.screens.recents.RecentsScreen
import com.sip.phoneclient.ui.screens.settings.SettingsScreen

/**
 * Navigation graph for the SIP Phone app
 * Defines all navigation routes and their associated screens
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dialer.route,
        modifier = modifier
    ) {
        // Dialer Screen - Default start destination
        composable(route = Screen.Dialer.route) {
            DialerScreen()
        }

        // Contacts Screen
        composable(route = Screen.Contacts.route) {
            ContactsScreen()
        }

        // Recents Screen
        composable(route = Screen.Recents.route) {
            RecentsScreen()
        }

        // Settings Screen
        composable(route = Screen.Settings.route) {
            SettingsScreen()
        }
    }
}
