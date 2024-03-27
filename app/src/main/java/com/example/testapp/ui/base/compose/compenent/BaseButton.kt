package com.example.testapp.ui.base.compose.compenent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    isPrimary: Boolean,
    text: String,
    fontSize: TextUnit = TextUnit.Unspecified,
    enabled: Boolean = true,
    uppercase: Boolean = true,
    onClick: () -> Unit,
    imageVector: ImageVector? = null
) {
    val (primaryColor, accentColor) = if (isPrimary) {
        MaterialTheme.colorScheme.primary to Color.White
    } else {
        Color.White to MaterialTheme.colorScheme.primary
    }
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(Height),
        colors = ButtonDefaults.buttonColors(
            containerColor = primaryColor,
            disabledContainerColor = MaterialTheme.colorScheme.background
        ),
        contentPadding = PaddingValues(),
        enabled = enabled,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (imageVector != null) {
                    Icon(
                        imageVector = imageVector,
                        modifier = Modifier.size(32.dp),
                        contentDescription = ""
                    )
                }
                Text(
                    color = when {
                        !enabled -> MaterialTheme.colorScheme.primary
                        else -> accentColor
                    },
                    text = if (uppercase) text.uppercase() else text,
                    textAlign = TextAlign.Center,
                    fontSize = fontSize
                )
            }
        }
    }
}

private val Height = 40.dp