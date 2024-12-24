package com.example.magic_demo.bootom_navigation

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import kotlin.text.Typography

@Composable
fun MaterialTheme(
    colors: Colors = MaterialTheme.colors,
    typography: com.example.magic_demo.bootom_navigation.Typography = MaterialTheme.typography,
    shapes: Shapes = MaterialTheme.shapes,
    content: @Composable () -> Unit
) {
    val rememberedColors = remember {
        // Explicitly creating a new object here so we don't mutate the initial [colors]
        // provided, and overwrite the values set in it.
        colors.copy()
    }.apply { updateColorsFrom(colors) }
    val rippleIndication = rememberRipple()
    val selectionColors = rememberTextSelectionColors(rememberedColors)
    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalContentAlpha provides ContentAlpha.high,
        LocalIndication provides rippleIndication,
        LocalRippleTheme provides MaterialRippleTheme,
        LocalShapes provides shapes,
        LocalTextSelectionColors provides selectionColors,
        LocalTypography provides typography
    ) {
        ProvideTextStyle(value = typography.body1) {
            PlatformMaterialTheme(content)
        }
    }
}

@Composable
internal fun PlatformMaterialTheme(content: @Composable () -> Unit) {
}

/**
 * Contains functions to access the current theme values provided at the call site's position in
 * the hierarchy.
 */
object MaterialTheme {
    /**
     * Retrieves the current [Colors] at the call site's position in the hierarchy.
     *
     * @sample androidx.compose.material.samples.ThemeColorSample
     */
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    /**
     * Retrieves the current [Typography] at the call site's position in the hierarchy.
     *
     * @sample androidx.compose.material.samples.ThemeTextStyleSample
     */
    val typography: com.example.magic_demo.bootom_navigation.Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    /**
     * Retrieves the current [Shapes] at the call site's position in the hierarchy.
     */
    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current
}

@Immutable
private object MaterialRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = RippleTheme.defaultRippleColor(
        contentColor = LocalContentColor.current,
        lightTheme = MaterialTheme.colors.isLight
    )

    @Composable
    override fun rippleAlpha() = RippleTheme.defaultRippleAlpha(
        contentColor = LocalContentColor.current,
        lightTheme = MaterialTheme.colors.isLight
    )
}
