package com.futuremind.loyaltyrewards.presentation.rewards.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.futuremind.loyaltyrewards.R
import com.futuremind.loyaltyrewards.domain.model.Reward
import com.futuremind.loyaltyrewards.presentation.common.BaseScreen
import com.futuremind.loyaltyrewards.presentation.rewards.RewardsViewModel
import com.futuremind.loyaltyrewards.presentation.common.components.TopBar
import com.futuremind.loyaltyrewards.presentation.common.theme.LocalColors
import com.futuremind.loyaltyrewards.presentation.rewards.RewardsContract
import com.futuremind.loyaltyrewards.presentation.rewards.ui.rewardsrow.RewardsRow

@Composable
fun RewardLayout(
    viewModel: RewardsViewModel = viewModel()
) = BaseScreen(
    displayProgressBar = viewModel.viewState.value.isLoading,
    errorsQueue = viewModel.viewState.value.errors,
    onRefresh = {
        viewModel.refresh()
    },
    content = {
        RewardLayout(
            points = viewModel.viewState.value.availablePoints,
            rewards = viewModel.viewState.value.rewards,
            onRewardClick = {
                if(it.activated) {
                    viewModel.sendEvent(RewardsContract.Event.ActiveRewardClicked(it.id))
                }
                else {
                    viewModel.sendEvent(RewardsContract.Event.InactiveRewardClicked(it.id))
                }
            }
        )
    }
)

@Composable
private fun RewardLayout(
    points: Int?,
    rewards: List<Reward>,
    onRewardClick: (Reward) -> Unit,
) {
    val errorSnackbarState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()

    Box {
        Column {
            TopBar(
                title = stringResource(R.string.rewards),
                onBack = { /* not part of task */ }
            )

            Column(
                modifier = Modifier
                    .navigationBarsPadding()
                    .background(LocalColors.current.gradientLight)
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                GreetingRow("FM Candidate") //not part of task
                Spacer(modifier = Modifier.height(24.dp))
                PointsSection(points = points)
                Spacer(modifier = Modifier.height(24.dp))
                RewardsRow(
                    rewards = rewards,
                    onItemClick = onRewardClick,
                    availablePoints = points
                )
                Spacer(modifier = Modifier.height(24.dp))
                ShareCard()
            }
        }
        SnackbarHost(
            hostState = errorSnackbarState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .systemBarsPadding()
        )
    }

}
