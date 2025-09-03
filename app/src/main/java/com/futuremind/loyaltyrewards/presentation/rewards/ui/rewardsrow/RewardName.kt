package com.futuremind.loyaltyrewards.presentation.rewards.ui.rewardsrow

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.futuremind.loyaltyrewards.domain.model.Reward
import com.futuremind.loyaltyrewards.presentation.common.theme.LocalTypography

@Composable
fun RewardName(
    reward: Reward,
    available: Boolean,
) {
    val textColor = if (available) MaterialTheme.colorScheme.onSurface
    else MaterialTheme.colorScheme.onSurfaceVariant
    Text(
        text = reward.name,
        style = LocalTypography.current.HeaderM,
        color = textColor,
        modifier = Modifier
            .padding(8.dp)
            .testTag(RewardsRowRewardNameTestTags.REWARDS_ROW_REWARD_NAME_TAG)
    )
}

object RewardsRowRewardNameTestTags {
    const val REWARDS_ROW_REWARD_NAME_TAG = "Rewards.RewardsRow.Reward.Name"
}