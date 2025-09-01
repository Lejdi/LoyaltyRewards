package com.futuremind.loyaltyrewards.presentation.rewards.ui

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.futuremind.loyaltyrewards.R
import com.futuremind.loyaltyrewards.presentation.common.BaseScreen
import com.futuremind.loyaltyrewards.presentation.rewards.RewardsViewModel
import com.futuremind.loyaltyrewards.presentation.common.components.ButtonLarge
import com.futuremind.loyaltyrewards.presentation.common.components.ColoredCard
import com.futuremind.loyaltyrewards.presentation.common.components.IconButtonSmall
import com.futuremind.loyaltyrewards.presentation.common.components.TopBar
import com.futuremind.loyaltyrewards.presentation.common.theme.LocalColors
import com.futuremind.loyaltyrewards.presentation.common.theme.LocalTypography

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
        )
    }
)

@Composable
private fun RewardLayout(
    points: Int?,
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

@Composable
fun GreetingRow(userName: String) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.hi_user, userName),
                style = LocalTypography.current.HeaderL.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = stringResource(R.string.welcome_to_club),
                style = LocalTypography.current.BodyL
            )
        }
        IconButtonSmall(
            iconPainter = painterResource(R.drawable.ic_card),
            onClick = { /* not part of task */ }
        )
    }
}

@Composable
fun PointsSection(
    points: Int?
) {

    val animatedPoints by animateIntAsState(targetValue = points ?: 0, label = "animatedPoints")
    val pointsText = when (points) {
        null -> "-"
        else -> animatedPoints.toString()
    }

    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(R.drawable.ic_gift), contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row {
                Text(
                    text = pointsText,
                    style = LocalTypography.current.HeaderL,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.points),
                    style = LocalTypography.current.HeaderL
                )
            }
            Text(
                text = stringResource(R.string.redeem_points_subtitle),
                style = LocalTypography.current.BodyM
            )
        }
    }
}

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
