package com.sip.phoneclient.data.models

/**
 * Contact data model
 */
data class Contact(
    val id: String,
    val name: String,
    val phoneNumber: String,
    val email: String? = null,
    val photoUrl: String? = null,
    val isFavorite: Boolean = false,
    val phoneType: PhoneType = PhoneType.MOBILE
)

enum class PhoneType {
    MOBILE,
    WORK,
    HOME,
    OTHER
}

/**
 * Generate initials from contact name for avatar
 */
fun Contact.getInitials(): String {
    return name.split(" ")
        .take(2)
        .mapNotNull { it.firstOrNull()?.uppercaseChar() }
        .joinToString("")
}
