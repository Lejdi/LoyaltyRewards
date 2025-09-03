package com.futuremind.loyaltyrewards.data.repository

import com.futuremind.loyaltyrewards.api.ApiPoints
import com.futuremind.loyaltyrewards.api.ApiReward
import com.futuremind.loyaltyrewards.api.ApiRewardActivationStatus
import com.futuremind.loyaltyrewards.data.utils.DataSourceResult

object MockRewardsRepository : RewardsRepository {
    private val rewards = mutableListOf<ApiReward>()
    private val rewardsStatuses = mutableListOf<ApiRewardActivationStatus>()
    private var points: Int = 0

    fun clearMockedRewards() {
        rewards.clear()
        rewardsStatuses.clear()
    }

    fun setRewards(
        mockedRewards: List<ApiReward>,
        mockedRewardsStatuses: List<ApiRewardActivationStatus>
    ) {
        rewards.addAll(mockedRewards)
        rewardsStatuses.addAll(mockedRewardsStatuses)
    }

    fun setPoints(mockedPoints: Int) {
        points = mockedPoints
    }

    override suspend fun changeRewardStatus(
        rewardId: Int,
        count: Int
    ): DataSourceResult<Unit> {
        rewardsStatuses.removeAll { it.id == rewardId }
        rewardsStatuses.add(
            ApiRewardActivationStatus(
                id = rewardId,
                activatedCount = count
            )
        )
        return DataSourceResult.Success(Unit)
    }

    override suspend fun getAvailablePoints() = DataSourceResult.Success(ApiPoints(points))

    override suspend fun getRewardsList() = DataSourceResult.Success(rewards.toList())

    override suspend fun getRewardsActivationList() = DataSourceResult.Success(rewardsStatuses.toList())

}