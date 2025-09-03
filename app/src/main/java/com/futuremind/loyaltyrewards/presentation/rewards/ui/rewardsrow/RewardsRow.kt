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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.futuremind.loyaltyrewards.R
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
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .testTag(RewardsRowTestTags.REWARDS_ROW_TAG)
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
                .height(100.dp)
                .testTag(RewardsRowTestTags.REWARDS_ROW_EMPTY_LIST_TAG),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.no_rewards),
                style = LocalTypography.current.HeaderM,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}


object RewardsRowTestTags {
    const val REWARDS_ROW_TAG = "Rewards.RewardsRow"
    const val REWARDS_ROW_EMPTY_LIST_TAG = "Rewards.RewardsRow.EmptyList"
}
