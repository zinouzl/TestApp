package com.example.testapp.ui.base.compose.compenent

import androidx.compose.runtime.Composable
import androidx.compose.ui.UiComposable
import com.example.testapp.ui.base.compose.DialogScope
import com.example.testapp.ui.base.compose.DialogScopeInstance

@Composable
fun PositionalDialog(
    content: @Composable @UiComposable DialogScope.() -> Unit
) = DialogScopeInstance.content()
