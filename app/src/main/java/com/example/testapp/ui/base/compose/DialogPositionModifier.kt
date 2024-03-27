package com.example.testapp.ui.base.compose

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.unit.Constraints

enum class DialogPosition {
    BOTTOM, TOP
}

internal class DialogPositionElement(
    var position: DialogPosition
) : ModifierNodeElement<DialogPositionNode>() {
    override fun create(): DialogPositionNode {
        return DialogPositionNode(position)
    }

    override fun equals(other: Any?): Boolean {
        val otherModifierElement = other as? DialogPositionElement ?: return false
        return position == otherModifierElement.position
    }

    override fun hashCode(): Int {
        return position.hashCode()
    }

    override fun update(node: DialogPositionNode) {
        node.position = position
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "position"
        value = position
    }
}

internal class DialogPositionNode(
    var position: DialogPosition
) : LayoutModifierNode, Modifier.Node() {
    override fun MeasureScope.measure(measurable: Measurable, constraints: Constraints): MeasureResult {
        val placeable = measurable.measure(constraints)
        return layout(constraints.maxWidth, constraints.maxHeight) {
            when (position) {
                DialogPosition.BOTTOM -> {
                    placeable.place(0, constraints.maxHeight - placeable.height, 10f)
                }

                DialogPosition.TOP -> {
                    placeable.place(0, 0, 10f)
                }
            }
        }
    }
}
