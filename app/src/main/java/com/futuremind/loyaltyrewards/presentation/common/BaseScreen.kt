package com.futuremind.loyaltyrewards.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.futuremind.loyaltyrewards.R
import com.futuremind.loyaltyrewards.presentation.common.theme.Palette
import com.futuremind.loyaltyrewards.presentation.common.utils.clickableWithoutRipple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(
    displayProgressBar: Boolean,
    errorsQueue: ErrorsQueue,
    content: @Composable () -> Unit,
    onRefresh: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        PullToRefreshBox(
            isRefreshing = displayProgressBar,
            onRefresh = onRefresh
        ){
            content()
        }
        if (displayProgressBar) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Palette.black.copy(
                            alpha = 0.5f
                        )
                    )
                    .clickableWithoutRipple {}
            ) {
                CircularProgressIndicator()
            }
        }
        errorsQueue.getError()?.let { error ->
            AlertDialog(
                containerColor = Color.White,
                title = {
                    Text(
                        text = stringResource(R.string.error),
                    )
                },
                text = {
                    Text(
                        text = stringResource(error.details, error.detailsArg),
                    )
                },
                onDismissRequest = {
                    // do nothing
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            errorsQueue.removeError()
                        },
                    ) {
                        Text(stringResource(R.string.ok))
                    }
                },
            )
        }
    }
}
