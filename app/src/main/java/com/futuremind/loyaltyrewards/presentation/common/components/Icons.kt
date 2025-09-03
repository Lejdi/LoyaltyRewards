package com.futuremind.loyaltyrewards.presentation.common.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics

val ImageResourceId = SemanticsPropertyKey<Int>(name = "ImageResourceID")

var SemanticsPropertyReceiver.imageResourceId by ImageResourceId

fun Modifier.painterResource(@DrawableRes id: Int): Modifier {
    return semantics {
        imageResourceId = id
    }
}

@Composable
fun ResourceIcon(
    @DrawableRes id: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier,
){
    Image(
        painter = painterResource(id),
        contentDescription = contentDescription,
        modifier = modifier
            .painterResource(id),
    )
}