package com.futuremind.loyaltyrewards.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.futuremind.loyaltyrewards.presentation.common.theme.LocalColors

@Composable
fun ColoredCard(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(brush = LocalColors.current.gradientCard)
            .padding(20.dp)
    ) {
        content()
    }
}