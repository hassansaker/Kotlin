package com.sip.phoneclient.ui.screens.recents.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sip.phoneclient.data.models.CallRecord
import com.sip.phoneclient.data.models.CallType
import com.sip.phoneclient.data.models.getFormattedDuration
import com.sip.phoneclient.data.models.getIcon
import java.time.format.DateTimeFormatter

@Composable
fun CallHistoryCard(
    callRecord: CallRecord,
    onCallBack: (CallRecord) -> Unit,
    onDelete: (CallRecord) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (callRecord.callType == CallType.MISSED && !callRecord.isRead)
                MaterialTheme.colorScheme.errorContainer
            else
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Call Info
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = callRecord.callType.getIcon(),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = callRecord.contactName ?: callRecord.phoneNumber,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                if (callRecord.contactName != null) {
                    Text(
                        text = callRecord.phoneNumber,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Row {
                    Text(
                        text = callRecord.timestamp.format(DateTimeFormatter.ofPattern("MMM dd, hh:mm a")),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    if (callRecord.duration > 0) {
                        Text(
                            text = " • ${callRecord.getFormattedDuration()}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Action buttons
            Row {
                IconButton(onClick = { onDelete(callRecord) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
                IconButton(onClick = { onCallBack(callRecord) }) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Call back",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}
