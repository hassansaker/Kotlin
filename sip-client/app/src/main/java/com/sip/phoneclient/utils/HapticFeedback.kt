package com.sip.phoneclient.utils

import android.view.HapticFeedbackConstants
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView

/**
 * Haptic Feedback Utility
 * Provides haptic feedback for button presses
 */
object HapticFeedback {
    /**
     * Perform a light click haptic feedback
     */
    fun performClick(view: View) {
        view.performHapticFeedback(
            HapticFeedbackConstants.KEYBOARD_TAP,
            HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
        )
    }

    /**
     * Perform a long press haptic feedback
     */
    fun performLongPress(view: View) {
        view.performHapticFeedback(
            HapticFeedbackConstants.LONG_PRESS,
            HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
        )
    }
}

/**
 * Composable function to get haptic feedback performer
 */
@Composable
fun rememberHapticFeedback(): (HapticFeedbackType) -> Unit {
    val view = LocalView.current
    return { type ->
        when (type) {
            HapticFeedbackType.Click -> HapticFeedback.performClick(view)
            HapticFeedbackType.LongPress -> HapticFeedback.performLongPress(view)
        }
    }
}

enum class HapticFeedbackType {
    Click,
    LongPress
}
