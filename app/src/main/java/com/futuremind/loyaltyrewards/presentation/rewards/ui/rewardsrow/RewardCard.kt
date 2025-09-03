package com.futuremind.loyaltyrewards.presentation.rewards.ui.rewardsrow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.futuremind.loyaltyrewards.R
import com.futuremind.loyaltyrewards.domain.model.Reward

@Composable
fun RewardCard(
    reward: Reward,
    onClick: (Reward) -> Unit,
    available: Boolean,
) {
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.small
            )
            .testTag(RewardsRowRewardCardTestTags.REWARDS_ROW_REWARD_CARD_TAG),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imageBlur = if (available) 0.dp else 4.dp
        SubcomposeAsyncImage(
            model = reward.coverUrl,
            contentDescription = reward.name,
            modifier = Modifier
                .width(200.dp)
                .blur(radius = imageBlur)
                .clip(RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp)),
            loading = {
                ImagePlaceholder()
            },
            error = {
                ImagePlaceholder()
            },
            contentScale = ContentScale.FillWidth,
        )
        Spacer(modifier = Modifier.height(12.dp))
        RewardName(
            reward = reward,
            available = available
        )
        Spacer(modifier = Modifier.height(8.dp))
        RewardButton(
            reward = reward,
            available = available,
            onClick = onClick
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ImagePlaceholder() {
    Column(
        modifier = Modifier
            .size(200.dp)
            .testTag(RewardsRowRewardCardTestTags.REWARDS_ROW_REWARD_CARD_PLACEHOLDER_TAG),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_gift),
            contentDescription = null,
        )
    }
}

object RewardsRowRewardCardTestTags {
    const val REWARDS_ROW_REWARD_CARD_TAG = "Rewards.RewardsRow.Reward.Card"
    const val REWARDS_ROW_REWARD_CARD_PLACEHOLDER_TAG = "Rewards.RewardsRow.Reward.Card.Placeholder"
}