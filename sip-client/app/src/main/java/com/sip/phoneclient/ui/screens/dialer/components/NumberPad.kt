package com.sip.phoneclient.ui.screens.dialer.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Number Pad Component
 * 12-button keypad layout (1-9, *, 0, #) with letters
 */
@Composable
fun NumberPad(
    onNumberClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Row 1: 1, 2, 3
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DialerButton(number = "1", letters = "", onClick = { onNumberClick("1") })
            DialerButton(number = "2", letters = "ABC", onClick = { onNumberClick("2") })
            DialerButton(number = "3", letters = "DEF", onClick = { onNumberClick("3") })
        }

        // Row 2: 4, 5, 6
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DialerButton(number = "4", letters = "GHI", onClick = { onNumberClick("4") })
            DialerButton(number = "5", letters = "JKL", onClick = { onNumberClick("5") })
            DialerButton(number = "6", letters = "MNO", onClick = { onNumberClick("6") })
        }

        // Row 3: 7, 8, 9
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DialerButton(number = "7", letters = "PQRS", onClick = { onNumberClick("7") })
            DialerButton(number = "8", letters = "TUV", onClick = { onNumberClick("8") })
            DialerButton(number = "9", letters = "WXYZ", onClick = { onNumberClick("9") })
        }

        // Row 4: *, 0, #
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DialerButton(number = "*", letters = "", onClick = { onNumberClick("*") })
            DialerButton(number = "0", letters = "+", onClick = { onNumberClick("0") })
            DialerButton(number = "#", letters = "", onClick = { onNumberClick("#") })
        }
    }
}

/**
 * Individual dialer button with number and letters
 */
@Composable
private fun DialerButton(
    number: String,
    letters: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier.size(80.dp),
        shape = CircleShape,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Number
            Text(
                text = number,
                fontSize = 28.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface
            )

            // Letters (if any)
            if (letters.isNotEmpty()) {
                Text(
                    text = letters,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
