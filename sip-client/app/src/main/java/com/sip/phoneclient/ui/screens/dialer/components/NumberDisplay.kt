package com.sip.phoneclient.ui.screens.dialer.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Number Display Component
 * Shows the entered phone number with formatting and clear/backspace button
 */
@Composable
fun NumberDisplay(
    number: String,
    onBackspace: () -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Phone number display
            Text(
                text = if (number.isEmpty()) "Enter number" else number,
                style = MaterialTheme.typography.headlineMedium,
                color = if (number.isEmpty())
                    MaterialTheme.colorScheme.onSurfaceVariant
                else
                    MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )

            // Backspace/Clear button
            if (number.isNotEmpty()) {
                IconButton(onClick = {
                    if (number.length > 1) {
                        onBackspace()
                    } else {
                        onClear()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Backspace,
                        contentDescription = if (number.length > 1) "Backspace" else "Clear",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
