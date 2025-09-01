package com.futuremind.loyaltyrewards.presentation.rewards

import androidx.lifecycle.viewModelScope
import com.futuremind.loyaltyrewards.domain.usecases.rewards.RewardsUseCases
import com.futuremind.loyaltyrewards.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RewardsViewModel @Inject constructor(
    private val useCases: RewardsUseCases,
) : BaseViewModel<RewardsContract.Event, RewardsContract.State, RewardsContract.Effect>() {

    init {
        refresh()
    }

    fun refresh() {
        getAvailablePoints {
            getRewards()
        }
    }

    private fun getAvailablePoints(
        onResult: () -> Unit = {},
    ) {
        setState {
            copy(
                isLoading = true
            )
        }
        useCases.getAvailablePoints(
            params = Unit,
            scope = viewModelScope,
            onResult = { result ->
                val points = if (result.isFailure()) {
                    viewState.value.availablePoints
                } else {
                    result.getResultData()
                }

                if (result.isFailure()) errorsQueue.addError(result.getErrorType())

                setState {
                    copy(
                        isLoading = false,
                        availablePoints = points,
                    )
                }
                onResult()
            }
        )
    }

    private fun getRewards() {
        setState {
            copy(
                isLoading = true
            )
        }
        useCases.getRewards(
            params = Unit,
            scope = viewModelScope,
            onResult = { result ->
                val rewards = if (result.isFailure()) {
                    viewState.value.rewards
                } else {
                    result.getResultData()
                }

                if (result.isFailure()) errorsQueue.addError(result.getErrorType())

                setState {
                    copy(
                        isLoading = false,
                        rewards = rewards,
                    )
                }
            }
        )
    }

    override fun setInitialState(): RewardsContract.State {
        return RewardsContract.State(
            isLoading = true,
            errors = errorsQueue,
            availablePoints = null,
            rewards = emptyList()
        )
    }

    override fun sendEvent(event: RewardsContract.Event) {
        when (event) {
            RewardsContract.Event.ActiveRewardClicked -> {
                //todo
            }

            RewardsContract.Event.InactiveRewardClicked -> {
                //todo
            }
        }
    }

}