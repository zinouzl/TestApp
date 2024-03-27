package com.example.testapp.ui.base.compose

import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier

@LayoutScopeMarker
@Immutable
@JvmDefaultWithCompatibility
interface DialogScope {

    @Stable
    fun Modifier.position(dialogPosition: DialogPosition): Modifier
}

internal object DialogScopeInstance : DialogScope {

    @Stable
    override fun Modifier.position(dialogPosition: DialogPosition): Modifier = this.then(
        DialogPositionElement(
            position = dialogPosition
        )
    )
}