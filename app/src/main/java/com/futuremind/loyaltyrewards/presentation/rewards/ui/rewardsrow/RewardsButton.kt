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
    val buttonMode = if (reward.activated) RewardsButtonMode.ACTIVATED
    else if (!available) RewardsButtonMode.NOT_AVAILABLE
    else RewardsButtonMode.AVAILABLE

    val icon = when (buttonMode) {
        RewardsButtonMode.ACTIVATED -> R.drawable.ic_check
        RewardsButtonMode.AVAILABLE -> R.drawable.ic_unlock
        RewardsButtonMode.NOT_AVAILABLE -> R.drawable.ic_lock
    }

    val description = when (buttonMode) {
        RewardsButtonMode.ACTIVATED -> R.string.reward_button_activated
        RewardsButtonMode.AVAILABLE -> R.string.reward_button_available
        RewardsButtonMode.NOT_AVAILABLE -> R.string.reward_button_not_available
    }

    val backgroundColor = if (!available) MaterialTheme.colorScheme.tertiary else
        MaterialTheme.colorScheme.secondary
    Row(
        modifier = Modifier
            .height(32.dp)
            .then(
                when (buttonMode) {
                    RewardsButtonMode.ACTIVATED -> {
                        Modifier
                            .background(
                                brush = LocalColors.current.gradientPrimaryButton,
                                shape = MaterialTheme.shapes.large
                            )
                    }

                    else -> {
                        Modifier.background(
                            color = backgroundColor,
                            shape = MaterialTheme.shapes.large
                        )
                    }
                }
            )
            .clip(MaterialTheme.shapes.large)
            .clickable(
                enabled = buttonMode != RewardsButtonMode.NOT_AVAILABLE,
            ) {
                onClick(reward)
            }
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .testTag(RewardsRowRewardButtonTestTags.REWARDS_ROW_REWARD_BUTTON_TAG),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ResourceIcon(
            id = icon,
            contentDescription = stringResource(description),
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

enum class RewardsButtonMode {
    ACTIVATED,
    AVAILABLE,
    NOT_AVAILABLE
}

object RewardsRowRewardButtonTestTags {
    const val REWARDS_ROW_REWARD_BUTTON_TAG = "Rewards.RewardsRow.Reward.Button"
    const val REWARDS_ROW_REWARD_BUTTON_ICON_TAG = "Rewards.RewardsRow.Reward.Button.Icon"
    const val REWARDS_ROW_REWARD_BUTTON_PRICE_TAG = "Rewards.RewardsRow.Reward.Button.Price"
}