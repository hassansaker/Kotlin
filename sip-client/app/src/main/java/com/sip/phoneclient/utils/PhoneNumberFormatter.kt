package com.sip.phoneclient.utils

import com.google.i18n.phonenumbers.PhoneNumberUtil
import java.util.*

/**
 * Phone Number Formatter Utility
 * Uses Google's libphonenumber library for international formatting
 */
object PhoneNumberFormatter {
    private val phoneNumberUtil = PhoneNumberUtil.getInstance()

    /**
     * Format phone number as user types
     * @param rawNumber The raw number string (may contain +, *, #)
     * @param defaultRegion The default region code (e.g., "US")
     * @return Formatted phone number
     */
    fun formatAsYouType(rawNumber: String, defaultRegion: String = getDefaultRegion()): String {
        if (rawNumber.isEmpty()) return ""

        // Don't format if it contains * or #
        if (rawNumber.contains("*") || rawNumber.contains("#")) {
            return rawNumber
        }

        return try {
            // Use PhoneNumberUtil to create formatter
            val formatter = phoneNumberUtil.getAsYouTypeFormatter(defaultRegion)
            var formatted = ""
            for (char in rawNumber) {
                formatted = formatter.inputDigit(char)
            }
            formatted
        } catch (e: Exception) {
            // If formatting fails, return original number
            rawNumber
        }
    }

    /**
     * Get the default region based on device locale
     */
    private fun getDefaultRegion(): String {
        return Locale.getDefault().country.ifEmpty { "US" }
    }

    /**
     * Check if a number is valid
     */
    fun isValidNumber(number: String, region: String = getDefaultRegion()): Boolean {
        return try {
            val phoneNumber = phoneNumberUtil.parse(number, region)
            phoneNumberUtil.isValidNumber(phoneNumber)
        } catch (e: Exception) {
            false
        }
    }
}
