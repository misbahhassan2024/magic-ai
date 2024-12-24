package com.example.magic_demo.bootom_navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.ln

@Composable
fun Surface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(color),
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    val LocalAbsoluteElevation = compositionLocalOf { 0.dp }
    val LocalElevationOverlay: ProvidableCompositionLocal<ElevationOverlay?> =
        staticCompositionLocalOf { DefaultElevationOverlay }

    val absoluteElevation = LocalAbsoluteElevation.current + elevation
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalAbsoluteElevation provides absoluteElevation
    ) {
        Box(
            modifier = modifier
                .surface(
                    shape = shape,
                    backgroundColor = surfaceColorAtElevation(
                        color = color,
                        elevationOverlay = LocalElevationOverlay.current,
                        absoluteElevation = absoluteElevation
                    ),
                    border = border,
                    elevation = elevation
                )
                .semantics(mergeDescendants = false) {}
                .pointerInput(Unit) {},
            propagateMinConstraints = true
        ) {
            content()
        }
    }
}







@Composable
@Deprecated(
    "This API is deprecated with the introduction a newer Surface function" +
            " overload that accepts an onClick().",
    replaceWith = ReplaceWith(
        expression = "Surface(onClick, modifier, enabled, shape, color, contentColor, border," +
                " elevation, interactionSource, content)"
    ),
    level = DeprecationLevel.ERROR
)
fun Surface(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(color),
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    indication: Indication? = LocalIndication.current,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    content: @Composable () -> Unit
) {
    val absoluteElevation = LocalAbsoluteElevation.current + elevation
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalAbsoluteElevation provides absoluteElevation
    ) {
        Box(
            modifier
                .minimumTouchTargetSize()
                .surface(
                    shape = shape,
                    backgroundColor = surfaceColorAtElevation(
                        color = color,
                        elevationOverlay = LocalElevationOverlay.current,
                        absoluteElevation = absoluteElevation
                    ),
                    border = border,
                    elevation = elevation
                )
                .then(
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = indication,
                        enabled = enabled,
                        onClickLabel = onClickLabel,
                        role = role,
                        onClick = onClick
                    )
                ),
            propagateMinConstraints = true
        ) {
            content()
        }
    }
}

private fun Modifier.surface(
    shape: Shape,
    backgroundColor: Color,
    border: BorderStroke?,
    elevation: Dp
) = this.shadow(elevation, shape, clip = false)
    .then(if (border != null) Modifier.border(border, shape) else Modifier)
    .background(color = backgroundColor, shape = shape)
    .clip(shape)

@Composable
private fun surfaceColorAtElevation(
    color: Color,
    elevationOverlay: ElevationOverlay?,
    absoluteElevation: Dp
): Color {
    return if (color == MaterialTheme.colorScheme.surface && elevationOverlay != null) {
        elevationOverlay.apply(color, absoluteElevation)
    } else {
        color
    }
}


