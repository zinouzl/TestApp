package com.example.testapp.ui.base.compose.compenent

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LargeButtonPrimary(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    uppercase: Boolean = true,
    onClick: () -> Unit,
    imageVector: ImageVector? = null
) {
    BaseButton(
        modifier = modifier,
        isPrimary = true,
        text = text,
        enabled = enabled,
        uppercase = uppercase,
        onClick = onClick,
        imageVector = imageVector
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LargeButtonPreview() {
    LargeButtonPrimary(
        text = "button",
        onClick = {}
    )
}