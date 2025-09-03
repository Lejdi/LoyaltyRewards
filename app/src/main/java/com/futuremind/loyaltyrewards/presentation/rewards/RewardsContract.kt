package com.futuremind.loyaltyrewards.presentation.rewards

import com.futuremind.loyaltyrewards.domain.model.Reward
import com.futuremind.loyaltyrewards.presentation.common.ErrorsQueue
import com.futuremind.loyaltyrewards.presentation.common.ViewEffect
import com.futuremind.loyaltyrewards.presentation.common.ViewEvent
import com.futuremind.loyaltyrewards.presentation.common.ViewState

class RewardsContract {

    sealed interface Event : ViewEvent {
        data class InactiveRewardClicked(val rewardId: Int) : Event
        data class ActiveRewardClicked(val rewardId: Int) : Event
    }

    data class State(
        var isLoading: Boolean,
        var errors: ErrorsQueue,
        var availablePoints: Int?,
        var rewards: List<Reward>
    ) : ViewState

    sealed interface Effect : ViewEffect
}