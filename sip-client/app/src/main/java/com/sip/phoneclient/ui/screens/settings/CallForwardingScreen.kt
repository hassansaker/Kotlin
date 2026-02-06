package com.sip.phoneclient.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sip.phoneclient.ui.theme.SIPPhoneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallForwardingScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var forwardingEnabled by remember { mutableStateOf(false) }
    var forwardAlways by remember { mutableStateOf(false) }
    var forwardBusy by remember { mutableStateOf(false) }
    var forwardNoAnswer by remember { mutableStateOf(false) }
    var forwardNotReachable by remember { mutableStateOf(false) }
    var forwardNumber by remember { mutableStateOf("") }
    var noAnswerTimeout by remember { mutableStateOf("20") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Call Forwarding") },
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
            // Master toggle
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Enable Call Forwarding",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "Forward calls to another number",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = forwardingEnabled,
                        onCheckedChange = { forwardingEnabled = it }
                    )
                }
            }

            // Forward number
            OutlinedTextField(
                value = forwardNumber,
                onValueChange = { forwardNumber = it },
                label = { Text("Forward To Number") },
                placeholder = { Text("e.g. +1234567890") },
                singleLine = true,
                enabled = forwardingEnabled,
                modifier = Modifier.fillMaxWidth()
            )

            // Forwarding conditions
            Text(
                text = "Forwarding Conditions",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            ForwardingConditionItem(
                title = "Always",
                subtitle = "Forward all incoming calls",
                checked = forwardAlways,
                onCheckedChange = { forwardAlways = it },
                enabled = forwardingEnabled
            )

            ForwardingConditionItem(
                title = "When Busy",
                subtitle = "Forward when on another call",
                checked = forwardBusy,
                onCheckedChange = { forwardBusy = it },
                enabled = forwardingEnabled && !forwardAlways
            )

            ForwardingConditionItem(
                title = "No Answer",
                subtitle = "Forward when call is not answered",
                checked = forwardNoAnswer,
                onCheckedChange = { forwardNoAnswer = it },
                enabled = forwardingEnabled && !forwardAlways
            )

            if (forwardNoAnswer && forwardingEnabled) {
                OutlinedTextField(
                    value = noAnswerTimeout,
                    onValueChange = { noAnswerTimeout = it },
                    label = { Text("No Answer Timeout (seconds)") },
                    placeholder = { Text("20") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            ForwardingConditionItem(
                title = "Not Reachable",
                subtitle = "Forward when device is offline",
                checked = forwardNotReachable,
                onCheckedChange = { forwardNotReachable = it },
                enabled = forwardingEnabled && !forwardAlways
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { /* TODO: Save forwarding settings */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }
        }
    }
}

@Composable
private fun ForwardingConditionItem(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean,
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
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    color = if (enabled) MaterialTheme.colorScheme.onSurface
                        else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (enabled) MaterialTheme.colorScheme.onSurfaceVariant
                        else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f)
                )
            }
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CallForwardingScreenPreview() {
    SIPPhoneTheme {
        CallForwardingScreen(onNavigateBack = {})
    }
}
