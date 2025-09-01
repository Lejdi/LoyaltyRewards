package com.futuremind.loyaltyrewards.presentation.rewards.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.futuremind.loyaltyrewards.R
import com.futuremind.loyaltyrewards.presentation.common.components.ButtonLarge
import com.futuremind.loyaltyrewards.presentation.common.components.ColoredCard
import com.futuremind.loyaltyrewards.presentation.common.theme.LocalTypography

@Composable
fun ShareCard() {
    ColoredCard {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.need_more_points_title),
                style = LocalTypography.current.HeaderM
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.need_more_points_body),
                style = LocalTypography.current.BodyM
            )
            Spacer(Modifier.height(16.dp))
            ButtonLarge(
                text = stringResource(R.string.share_invite_code),
                onClick = { /* not part of task */ },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}