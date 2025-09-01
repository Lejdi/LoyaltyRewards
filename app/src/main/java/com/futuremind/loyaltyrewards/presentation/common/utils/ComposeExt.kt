package com.futuremind.loyaltyrewards.presentation.common.utils

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier

fun Modifier.clickableWithoutRipple(onClick: () -> Unit) =
    this.then(
        Modifier.clickable(
            onClick = onClick,
            interactionSource = null,
            indication = null,
        ),
    )
