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