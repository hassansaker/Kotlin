package com.sip.phoneclient.data.repository

import com.sip.phoneclient.data.models.CallRecord
import com.sip.phoneclient.data.models.CallType
import java.time.LocalDateTime

/**
 * Mock Call Repository
 * Provides sample call history data for UI development
 */
object MockCallRepository {

    private val mockCallRecords = listOf(
        CallRecord(
            id = "1",
            contactName = "Alice Johnson",
            phoneNumber = "+1 (555) 123-4567",
            callType = CallType.INCOMING,
            timestamp = LocalDateTime.now().minusHours(1),
            duration = 324, // 5m 24s
            isRead = true
        ),
        CallRecord(
            id = "2",
            contactName = "Bob Smith",
            phoneNumber = "+1 (555) 234-5678",
            callType = CallType.OUTGOING,
            timestamp = LocalDateTime.now().minusHours(3),
            duration = 180, // 3m 00s
            isRead = true
        ),
        CallRecord(
            id = "3",
            contactName = null,
            phoneNumber = "+1 (555) 999-8888",
            callType = CallType.MISSED,
            timestamp = LocalDateTime.now().minusHours(5),
            duration = 0,
            isRead = false
        ),
        CallRecord(
            id = "4",
            contactName = "Emma Davis",
            phoneNumber = "+1 (555) 567-8901",
            callType = CallType.INCOMING,
            timestamp = LocalDateTime.now().minusDays(1),
            duration = 456, // 7m 36s
            isRead = true
        ),
        CallRecord(
            id = "5",
            contactName = "Frank Miller",
            phoneNumber = "+1 (555) 678-9012",
            callType = CallType.OUTGOING,
            timestamp = LocalDateTime.now().minusDays(1),
            duration = 120, // 2m 00s
            isRead = true
        ),
        CallRecord(
            id = "6",
            contactName = null,
            phoneNumber = "+1 (555) 111-2222",
            callType = CallType.MISSED,
            timestamp = LocalDateTime.now().minusDays(2),
            duration = 0,
            isRead = false
        ),
        CallRecord(
            id = "7",
            contactName = "Grace Wilson",
            phoneNumber = "+1 (555) 789-0123",
            callType = CallType.REJECTED,
            timestamp = LocalDateTime.now().minusDays(2),
            duration = 0,
            isRead = true
        ),
        CallRecord(
            id = "8",
            contactName = "Alice Johnson",
            phoneNumber = "+1 (555) 123-4567",
            callType = CallType.OUTGOING,
            timestamp = LocalDateTime.now().minusDays(3),
            duration = 245, // 4m 05s
            isRead = true
        )
    )

    /**
     * Get all call records
     */
    fun getAllCallRecords(): List<CallRecord> {
        return mockCallRecords.sortedByDescending { it.timestamp }
    }

    /**
     * Get call records filtered by type
     */
    fun getCallRecordsByType(type: CallType?): List<CallRecord> {
        return if (type == null) {
            getAllCallRecords()
        } else {
            mockCallRecords.filter { it.callType == type }
                .sortedByDescending { it.timestamp }
        }
    }

    /**
     * Get missed calls only
     */
    fun getMissedCalls(): List<CallRecord> {
        return mockCallRecords.filter { it.callType == CallType.MISSED }
            .sortedByDescending { it.timestamp }
    }
}
