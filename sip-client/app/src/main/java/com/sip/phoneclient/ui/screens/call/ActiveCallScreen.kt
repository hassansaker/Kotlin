package com.sip.phoneclient.ui.screens.call

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sip.phoneclient.ui.screens.call.components.*
import com.sip.phoneclient.ui.theme.SIPPhoneTheme

@Composable
fun ActiveCallScreen(
    callerName: String,
    callerNumber: String,
    onEndCall: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isMuted by remember { mutableStateOf(false) }
    var isSpeakerOn by remember { mutableStateOf(false) }
    var isOnHold by remember { mutableStateOf(false) }
    var showDTMFKeypad by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Caller Info
            CallerInfo(
                callerName = callerName,
                callerNumber = callerNumber,
                showPulse = false
            )

            // Call Timer
            Spacer(modifier = Modifier.height(12.dp))
            CallTimer(isRunning = !isOnHold)

            Spacer(modifier = Modifier.weight(1f))

            // Call Controls
            CallControls(
                isMuted = isMuted,
                isSpeakerOn = isSpeakerOn,
                isOnHold = isOnHold,
                onMuteToggle = { isMuted = !isMuted },
                onSpeakerToggle = { isSpeakerOn = !isSpeakerOn },
                onHoldToggle = { isOnHold = !isOnHold },
                onKeypadClick = { showDTMFKeypad = true },
                onAddCallClick = { /* TODO: Add call */ },
                onTransferClick = { /* TODO: Transfer call */ }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // End Call Button
            EndCallButton(onClick = onEndCall)

            Spacer(modifier = Modifier.height(24.dp))
        }

        // DTMF Keypad Overlay
        if (showDTMFKeypad) {
            DTMFKeypad(
                onDigitPress = { digit ->
                    // TODO: Send DTMF tone
                },
                onDismiss = { showDTMFKeypad = false }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ActiveCallScreenPreview() {
    SIPPhoneTheme {
        ActiveCallScreen(
            callerName = "John Smith",
            callerNumber = "+1 (234) 567-8900",
            onEndCall = {}
        )
    }
}
