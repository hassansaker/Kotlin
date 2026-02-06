package com.sip.phoneclient.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sip.phoneclient.ui.theme.SIPPhoneTheme

data class AudioCodecItem(
    val name: String,
    val clockRate: String,
    val bitrate: String,
    val enabled: Boolean = true
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioCodecScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var codecs by remember {
        mutableStateOf(
            listOf(
                AudioCodecItem("Opus", "48 kHz", "6-510 kbps", true),
                AudioCodecItem("G.722", "16 kHz", "64 kbps", true),
                AudioCodecItem("G.711 (PCMU)", "8 kHz", "64 kbps", true),
                AudioCodecItem("G.711 (PCMA)", "8 kHz", "64 kbps", false),
                AudioCodecItem("G.729", "8 kHz", "8 kbps", false),
                AudioCodecItem("GSM", "8 kHz", "13 kbps", false),
                AudioCodecItem("iLBC", "8 kHz", "15.2 kbps", false),
                AudioCodecItem("Speex", "8/16/32 kHz", "2-44 kbps", false)
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Audio Codecs") },
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text(
                    text = "Enable or disable audio codecs. Codecs at the top have higher priority.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            item {
                Text(
                    text = "Enabled",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            items(codecs.filter { it.enabled }) { codec ->
                CodecCard(
                    codec = codec,
                    onToggle = { toggled ->
                        codecs = codecs.map {
                            if (it.name == codec.name) it.copy(enabled = toggled) else it
                        }
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Disabled",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            items(codecs.filter { !it.enabled }) { codec ->
                CodecCard(
                    codec = codec,
                    onToggle = { toggled ->
                        codecs = codecs.map {
                            if (it.name == codec.name) it.copy(enabled = toggled) else it
                        }
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* TODO: Save codec preferences */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Preferences")
                }
            }
        }
    }
}

@Composable
private fun CodecCard(
    codec: AudioCodecItem,
    onToggle: (Boolean) -> Unit,
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
                    text = codec.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "${codec.clockRate} • ${codec.bitrate}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Switch(
                checked = codec.enabled,
                onCheckedChange = onToggle
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AudioCodecScreenPreview() {
    SIPPhoneTheme {
        AudioCodecScreen(onNavigateBack = {})
    }
}
