package com.sip.phoneclient.ui.screens.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sip.phoneclient.ui.theme.LocalThemeMode
import com.sip.phoneclient.ui.theme.SIPPhoneTheme
import com.sip.phoneclient.ui.theme.ThemeMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSettingsScreen(
    onNavigateBack: () -> Unit,
    onThemeChanged: (ThemeMode) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val currentTheme = LocalThemeMode.current
    var selectedTheme by remember { mutableStateOf(currentTheme) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Theme") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Appearance",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            ThemeOption(
                title = "System Default",
                subtitle = "Follow device theme settings",
                icon = Icons.Default.Smartphone,
                selected = selectedTheme == ThemeMode.SYSTEM,
                onClick = {
                    selectedTheme = ThemeMode.SYSTEM
                    onThemeChanged(ThemeMode.SYSTEM)
                }
            )

            ThemeOption(
                title = "Light",
                subtitle = "Always use light theme",
                icon = Icons.Default.LightMode,
                selected = selectedTheme == ThemeMode.LIGHT,
                onClick = {
                    selectedTheme = ThemeMode.LIGHT
                    onThemeChanged(ThemeMode.LIGHT)
                }
            )

            ThemeOption(
                title = "Dark",
                subtitle = "Always use dark theme",
                icon = Icons.Default.DarkMode,
                selected = selectedTheme == ThemeMode.DARK,
                onClick = {
                    selectedTheme = ThemeMode.DARK
                    onThemeChanged(ThemeMode.DARK)
                }
            )
        }
    }
}

@Composable
private fun ThemeOption(
    title: String,
    subtitle: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        border = if (selected) BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null,
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (selected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurfaceVariant
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            if (selected) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Selected",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ThemeSettingsScreenPreview() {
    SIPPhoneTheme {
        ThemeSettingsScreen(onNavigateBack = {})
    }
}
