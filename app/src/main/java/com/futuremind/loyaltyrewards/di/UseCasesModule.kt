package com.futuremind.loyaltyrewards.di

import com.futuremind.loyaltyrewards.data.repository.RewardsRepository
import com.futuremind.loyaltyrewards.domain.usecases.rewards.ActivateReward
import com.futuremind.loyaltyrewards.domain.usecases.rewards.DeactivateReward
import com.futuremind.loyaltyrewards.domain.usecases.rewards.GetAvailablePoints
import com.futuremind.loyaltyrewards.domain.usecases.rewards.GetRewards
import com.futuremind.loyaltyrewards.domain.usecases.rewards.RewardsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Singleton
    @Provides
    fun provideGetAvailablePoints(
        rewardsRepository: RewardsRepository
    ) : GetAvailablePoints {
        return GetAvailablePoints(rewardsRepository)
    }

    @Singleton
    @Provides
    fun provideGetRewards(
        rewardsRepository: RewardsRepository
    ) : GetRewards {
        return GetRewards(rewardsRepository)
    }

    @Singleton
    @Provides
    fun provideDeactivateReward(
        rewardsRepository: RewardsRepository
    ) : DeactivateReward {
        return DeactivateReward(rewardsRepository)
    }

    @Singleton
    @Provides
    fun provideActivateReward(
        rewardsRepository: RewardsRepository
    ) : ActivateReward {
        return ActivateReward(rewardsRepository)
    }


    @Singleton
    @Provides
    fun provideRewardsUseCases(
        getAvailablePoints: GetAvailablePoints,
        getRewards: GetRewards,
        deactivateReward: DeactivateReward,
        activateReward: ActivateReward,
    ) : RewardsUseCases {
        return RewardsUseCases(
            getAvailablePoints = getAvailablePoints,
            getRewards = getRewards,
            deactivateReward = deactivateReward,
            activateReward = activateReward
        )
    }
}