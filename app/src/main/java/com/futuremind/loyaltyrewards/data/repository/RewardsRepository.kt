package com.futuremind.loyaltyrewards.data.repository

import com.futuremind.loyaltyrewards.api.ApiPoints
import com.futuremind.loyaltyrewards.api.ApiReward
import com.futuremind.loyaltyrewards.api.ApiRewardActivationStatus
import com.futuremind.loyaltyrewards.data.utils.DataSourceResult

interface RewardsRepository {
    suspend fun getAvailablePoints(): DataSourceResult<ApiPoints>

    suspend fun getRewardsList(): DataSourceResult<List<ApiReward>>

    suspend fun getRewardsActivationList(): DataSourceResult<List<ApiRewardActivationStatus>>

    suspend fun changeRewardStatus(rewardId: Int, count: Int): DataSourceResult<Unit>
}