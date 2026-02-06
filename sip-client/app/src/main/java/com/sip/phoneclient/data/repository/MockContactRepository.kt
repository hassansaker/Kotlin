package com.sip.phoneclient.data.repository

import com.sip.phoneclient.data.models.Contact
import com.sip.phoneclient.data.models.PhoneType

/**
 * Mock Contact Repository
 * Provides sample contact data for UI development
 */
object MockContactRepository {

    private val mockContacts = listOf(
        Contact(
            id = "1",
            name = "Alice Johnson",
            phoneNumber = "+1 (555) 123-4567",
            email = "alice.johnson@email.com",
            isFavorite = true,
            phoneType = PhoneType.MOBILE
        ),
        Contact(
            id = "2",
            name = "Bob Smith",
            phoneNumber = "+1 (555) 234-5678",
            email = "bob.smith@email.com",
            isFavorite = true,
            phoneType = PhoneType.WORK
        ),
        Contact(
            id = "3",
            name = "Carol Williams",
            phoneNumber = "+1 (555) 345-6789",
            isFavorite = false,
            phoneType = PhoneType.MOBILE
        ),
        Contact(
            id = "4",
            name = "David Brown",
            phoneNumber = "+1 (555) 456-7890",
            isFavorite = false,
            phoneType = PhoneType.HOME
        ),
        Contact(
            id = "5",
            name = "Emma Davis",
            phoneNumber = "+1 (555) 567-8901",
            email = "emma.davis@email.com",
            isFavorite = true,
            phoneType = PhoneType.MOBILE
        ),
        Contact(
            id = "6",
            name = "Frank Miller",
            phoneNumber = "+1 (555) 678-9012",
            isFavorite = false,
            phoneType = PhoneType.WORK
        ),
        Contact(
            id = "7",
            name = "Grace Wilson",
            phoneNumber = "+1 (555) 789-0123",
            email = "grace.wilson@email.com",
            isFavorite = false,
            phoneType = PhoneType.MOBILE
        ),
        Contact(
            id = "8",
            name = "Henry Moore",
            phoneNumber = "+1 (555) 890-1234",
            isFavorite = false,
            phoneType = PhoneType.OTHER
        )
    )

    /**
     * Get all contacts
     */
    fun getAllContacts(): List<Contact> {
        return mockContacts.sortedBy { it.name }
    }

    /**
     * Get favorite contacts
     */
    fun getFavoriteContacts(): List<Contact> {
        return mockContacts.filter { it.isFavorite }.sortedBy { it.name }
    }

    /**
     * Search contacts by name or phone number
     */
    fun searchContacts(query: String): List<Contact> {
        if (query.isBlank()) return getAllContacts()

        val lowerQuery = query.lowercase()
        return mockContacts.filter {
            it.name.lowercase().contains(lowerQuery) ||
            it.phoneNumber.contains(query)
        }.sortedBy { it.name }
    }
}
