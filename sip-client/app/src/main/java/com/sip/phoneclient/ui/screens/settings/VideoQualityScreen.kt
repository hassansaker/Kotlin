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
fun VideoQualityScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedResolution by remember { mutableStateOf("720p") }
    var selectedFrameRate by remember { mutableStateOf("30") }
    var selectedCodec by remember { mutableStateOf("H.264") }
    var videoBandwidth by remember { mutableStateOf("1500") }
    var videoEnabled by remember { mutableStateOf(true) }

    val resolutions = listOf("480p", "720p", "1080p")
    val frameRates = listOf("15", "24", "30")
    val videoCodecs = listOf("H.264", "H.265", "VP8", "VP9")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Video Quality") },
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
            // Video Enable Toggle
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
                            text = "Enable Video Calls",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "Allow video during calls",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = videoEnabled,
                        onCheckedChange = { videoEnabled = it }
                    )
                }
            }

            // Resolution
            Text(
                text = "Resolution",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                resolutions.forEach { resolution ->
                    FilterChip(
                        selected = selectedResolution == resolution,
                        onClick = { selectedResolution = resolution },
                        label = { Text(resolution) },
                        enabled = videoEnabled
                    )
                }
            }

            // Frame Rate
            Text(
                text = "Frame Rate (fps)",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                frameRates.forEach { fps ->
                    FilterChip(
                        selected = selectedFrameRate == fps,
                        onClick = { selectedFrameRate = fps },
                        label = { Text("${fps} fps") },
                        enabled = videoEnabled
                    )
                }
            }

            // Video Codec
            Text(
                text = "Video Codec",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                videoCodecs.forEach { codec ->
                    FilterChip(
                        selected = selectedCodec == codec,
                        onClick = { selectedCodec = codec },
                        label = { Text(codec) },
                        enabled = videoEnabled
                    )
                }
            }

            // Bandwidth
            OutlinedTextField(
                value = videoBandwidth,
                onValueChange = { videoBandwidth = it },
                label = { Text("Max Bandwidth (kbps)") },
                placeholder = { Text("1500") },
                singleLine = true,
                enabled = videoEnabled,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { /* TODO: Save video settings */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VideoQualityScreenPreview() {
    SIPPhoneTheme {
        VideoQualityScreen(onNavigateBack = {})
    }
}
