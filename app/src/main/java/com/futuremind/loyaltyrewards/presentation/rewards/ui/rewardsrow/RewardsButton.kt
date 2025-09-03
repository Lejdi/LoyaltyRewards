package com.futuremind.loyaltyrewards.presentation.rewards.ui.rewardsrow

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.futuremind.loyaltyrewards.R
import com.futuremind.loyaltyrewards.domain.model.Reward
import com.futuremind.loyaltyrewards.presentation.common.components.ResourceIcon
import com.futuremind.loyaltyrewards.presentation.common.theme.LocalColors
import com.futuremind.loyaltyrewards.presentation.common.theme.LocalTypography

@Composable
fun RewardButton(
    reward: Reward,
    onClick: (Reward) -> Unit,
    available: Boolean,
) {
    val activated = reward.activated

    val icon = if (activated) R.drawable.ic_check
    else if (!available) R.drawable.ic_lock else
        R.drawable.ic_unlock

    val backgroundColor = if (!available) MaterialTheme.colorScheme.tertiary else
        MaterialTheme.colorScheme.secondary
    Row(
        modifier = Modifier
            .height(32.dp)
            .then(
                if (activated) {
                    Modifier
                        .background(
                            brush = LocalColors.current.gradientPrimaryButton,
                            shape = MaterialTheme.shapes.large
                        )
                } else {
                    Modifier.background(
                        color = backgroundColor,
                        shape = MaterialTheme.shapes.large
                    )
                }
            )
            .clip(MaterialTheme.shapes.large)
            .clickable(
                enabled = activated || available,
            ) {
                onClick(reward)
            }
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .testTag(RewardsRowRewardButtonTestTags.REWARDS_ROW_REWARD_BUTTON_TAG),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ResourceIcon(
            id = icon,
            modifier = Modifier
                .size(14.dp)
                .testTag(RewardsRowRewardButtonTestTags.REWARDS_ROW_REWARD_BUTTON_ICON_TAG),
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = reward.pointsCost.toString(),
            style = LocalTypography.current.BodyM,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .testTag(RewardsRowRewardButtonTestTags.REWARDS_ROW_REWARD_BUTTON_PRICE_TAG),
        )
        Text(
            text = " " + stringResource(R.string.points),
            style = LocalTypography.current.BodyM,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

object RewardsRowRewardButtonTestTags {
    const val REWARDS_ROW_REWARD_BUTTON_TAG = "Rewards.RewardsRow.Reward.Button"
    const val REWARDS_ROW_REWARD_BUTTON_ICON_TAG = "Rewards.RewardsRow.Reward.Button.Icon"
    const val REWARDS_ROW_REWARD_BUTTON_PRICE_TAG = "Rewards.RewardsRow.Reward.Button.Price"
}