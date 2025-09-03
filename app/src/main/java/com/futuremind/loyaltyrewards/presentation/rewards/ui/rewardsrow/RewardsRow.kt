package com.futuremind.loyaltyrewards.presentation.rewards.ui.rewardsrow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.futuremind.loyaltyrewards.domain.model.Reward
import com.futuremind.loyaltyrewards.presentation.common.theme.LocalTypography

@Composable
fun RewardsRow(
    rewards: List<Reward>,
    onItemClick: (Reward) -> Unit,
    availablePoints: Int?,
) {
    if (rewards.isNotEmpty()) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(rewards) {
                RewardCard(
                    reward = it,
                    onClick = onItemClick,
                    available = it.pointsCost < (availablePoints ?: 0)
                )
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "No rewards available",
                style = LocalTypography.current.HeaderM,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
