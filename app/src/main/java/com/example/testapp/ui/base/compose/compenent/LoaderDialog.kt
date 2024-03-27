package com.example.testapp.ui.base.compose.compenent

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.testapp.R
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun LoaderDialog() {
    Dialog(
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        onDismissRequest = {} // dismissing is not supported by loader dialog
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.loading))

            BallSpinFadeLoaderIndicator(
                color = Color.White,
                indicatorSize = IndicatorSize
            )
        }
    }
}

@Composable
fun BallSpinFadeLoaderIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    indicatorSize: Dp = DefaultIndicatorSize
) {
    val transition = rememberInfiniteTransition(label = "InfiniteTransition")
    val fraction by transition.fraction(AnimationDuration)

    Canvas(
        modifier = modifier
            .progressSemantics()
            .size(indicatorSize)
            .focusable(),
        onDraw = {
            val diameterList = mutableListOf<Float>()
            val alphaList = mutableListOf<Float>()
            val minSize = (indicatorSize.toPx() / 5) * MinScale
            val maxSize = (indicatorSize.toPx() / 5) * MaxScale

            for (i in 0..7) {
                val newFraction = getShiftedFraction(1 - fraction, i * .1f)
                lerp(minSize, maxSize, newFraction).also { diameterList.add(it) }
                lerp(MinAlpha, MaxAlpha, newFraction).also { alphaList.add(it) }
            }

            for (i in 0..7) {
                val radius = diameterList[i] / 2
                val angle = PI.toFloat() / 4 * i
                val x = (size.width - maxSize) * cos(angle) / 2
                val y = (size.height - maxSize) * sin(angle) / 2

                drawCircle(
                    color = color,
                    radius = radius,
                    center = center + Offset(x, y),
                    alpha = alphaList[i]
                )
            }
        }
    )
}

private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + ((stop - start) * fraction)
}

private fun getShiftedFraction(fraction: Float, shift: Float): Float {
    val newFraction = (fraction + shift) % 1
    return (if (newFraction > .5) 1 - newFraction else newFraction) * 2
}

@Composable
private fun InfiniteTransition.fraction(
    durationMillis: Int,
    delayMillis: Int = 0,
    easing: Easing = LinearEasing
): State<Float> {
    val duration = durationMillis + delayMillis

    return animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                this.durationMillis = duration
                0f at delayMillis / 2 with easing
                1f at durationMillis + (delayMillis / 2)
                1f at duration
            }
        ), label = "animateFloat"
    )
}

private  val IndicatorSize = 70.dp
private const val AnimationDuration = 1000

private val DefaultIndicatorSize = 40.dp

private const val MaxScale = 1f
private const val MinScale = .4f
private const val MaxAlpha = 1f
private const val MinAlpha = .3f