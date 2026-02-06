package com.sip.phoneclient.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sip.phoneclient.ui.theme.SIPPhoneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationStatusScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isRegistered by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registration Status") },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Status Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (isRegistered)
                        MaterialTheme.colorScheme.secondaryContainer
                    else
                        MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = if (isRegistered)
                            MaterialTheme.colorScheme.secondary
                        else
                            MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(40.dp)
                    )
                    Column {
                        Text(
                            text = if (isRegistered) "Registered" else "Not Registered",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = if (isRegistered) "Connected to SIP server"
                                else "Disconnected from server",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Connection Details
            Text(
                text = "Connection Details",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            StatusInfoRow(label = "Account", value = "user@sip.example.com")
            StatusInfoRow(label = "Server", value = "sip.example.com:5060")
            StatusInfoRow(label = "Transport", value = "UDP")
            StatusInfoRow(label = "Expires", value = "3600 seconds")
            StatusInfoRow(label = "Last Registered", value = "2 minutes ago")
            StatusInfoRow(label = "NAT Type", value = "Symmetric NAT")
            StatusInfoRow(label = "Public IP", value = "203.0.113.42")

            Spacer(modifier = Modifier.height(8.dp))

            // Register / Unregister button
            if (isRegistered) {
                OutlinedButton(
                    onClick = { isRegistered = false },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Unregister")
                }
            } else {
                Button(
                    onClick = { isRegistered = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Register")
                }
            }

            OutlinedButton(
                onClick = { /* TODO: Refresh registration */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Refresh Registration")
            }
        }
    }
}

@Composable
private fun StatusInfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegistrationStatusScreenPreview() {
    SIPPhoneTheme {
        RegistrationStatusScreen(onNavigateBack = {})
    }
}
