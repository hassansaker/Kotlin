package com.sip.phoneclient.data.models

import java.time.LocalDateTime

/**
 * Call Record data model for call history
 */
data class CallRecord(
    val id: String,
    val contactName: String?,
    val phoneNumber: String,
    val callType: CallType,
    val timestamp: LocalDateTime,
    val duration: Int, // Duration in seconds
    val isRead: Boolean = false
)

enum class CallType {
    INCOMING,
    OUTGOING,
    MISSED,
    REJECTED
}

/**
 * Get display icon for call type
 */
fun CallType.getIcon(): String {
    return when (this) {
        CallType.INCOMING -> "↙️"
        CallType.OUTGOING -> "↗️"
        CallType.MISSED -> "↖️"
        CallType.REJECTED -> "⏸️"
    }
}

/**
 * Format duration as MM:SS
 */
fun CallRecord.getFormattedDuration(): String {
    val minutes = duration / 60
    val seconds = duration % 60
    return String.format("%d:%02d", minutes, seconds)
}
