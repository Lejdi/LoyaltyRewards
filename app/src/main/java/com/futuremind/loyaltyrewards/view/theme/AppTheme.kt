package com.futuremind.loyaltyrewards.view.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalColors provides AppColors,
        LocalContentColor provides Palette.black,
        LocalTypography provides AppTypography()
    ) {
        MaterialTheme(
            colors = lightColors(
                primary = Palette.peach,
                background = Palette.white,
                secondary = Palette.black,
                surface = Palette.alabaster,
                onBackground = Palette.black,
                onPrimary = Palette.white,
                onSecondary = Palette.white
            ),
            typography = Typography(
                defaultFontFamily = fonts,
                body1 = LocalTypography.current.BodyM
            ),
            shapes = Shapes(
                medium = RoundedCornerShape(8.dp),
                large = RoundedCornerShape(16.dp)
            )
        ) {
            content()
        }
    }
}

