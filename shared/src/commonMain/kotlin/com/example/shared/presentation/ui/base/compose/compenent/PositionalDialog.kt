package com.example.shared.presentation.ui.base.compose.compenent

import androidx.compose.runtime.Composable
import androidx.compose.ui.UiComposable
import com.example.shared.presentation.ui.base.compose.DialogScope
import com.example.shared.presentation.ui.base.compose.DialogScopeInstance

@Composable
fun PositionalDialog(
    content: @Composable @UiComposable DialogScope.() -> Unit
) = DialogScopeInstance.content()
