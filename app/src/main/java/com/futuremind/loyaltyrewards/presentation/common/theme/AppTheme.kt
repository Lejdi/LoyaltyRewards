package com.futuremind.loyaltyrewards.presentation.common.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
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
        val defaultTypography = Typography()
        
        MaterialTheme(
            colorScheme = lightColorScheme(
                primary = Palette.peach,
                background = Palette.white,
                secondary = Palette.black,
                surface = Palette.alabaster,
                onBackground = Palette.black,
                onPrimary = Palette.white,
                onSecondary = Palette.white,
                onSurface = Palette.black,
                onSurfaceVariant = Palette.iron,
                tertiary = Palette.gray,
                onTertiary = Palette.white,
            ),
            typography = Typography(
                displayLarge = defaultTypography.displayLarge.copy(fontFamily = fonts),
                displayMedium = defaultTypography.displayMedium.copy(fontFamily = fonts),
                displaySmall = defaultTypography.displaySmall.copy(fontFamily = fonts),

                headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = fonts),
                headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = fonts),
                headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = fonts),

                titleLarge = defaultTypography.titleLarge.copy(fontFamily = fonts),
                titleMedium = defaultTypography.titleMedium.copy(fontFamily = fonts),
                titleSmall = defaultTypography.titleSmall.copy(fontFamily = fonts),

                bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = fonts),
                bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = fonts),
                bodySmall = defaultTypography.bodySmall.copy(fontFamily = fonts),

                labelLarge = defaultTypography.labelLarge.copy(fontFamily = fonts),
                labelMedium = defaultTypography.labelMedium.copy(fontFamily = fonts),
                labelSmall = defaultTypography.labelSmall.copy(fontFamily = fonts)
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

