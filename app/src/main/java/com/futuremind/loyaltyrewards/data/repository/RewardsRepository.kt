package com.futuremind.loyaltyrewards.data.repository

import com.futuremind.loyaltyrewards.api.RewardsApi
import com.futuremind.loyaltyrewards.data.utils.handleNetworkResponse

class RewardsRepository(
    val api: RewardsApi
) {
    suspend fun getAvailablePoints() = handleNetworkResponse {
        api.getPoints()
    }

    suspend fun getRewardsList() = handleNetworkResponse {
        api.getRewards()
    }

    suspend fun getRewardsActivationList() = handleNetworkResponse {
        api.getRewardsActivationStatus()
    }

    suspend fun changeRewardStatus(rewardId: Int, count: Int) = handleNetworkResponse {
        api.changeRewardActivationStatus(
            rewardId = rewardId,
            activationsCount = count,
        )
    }
}