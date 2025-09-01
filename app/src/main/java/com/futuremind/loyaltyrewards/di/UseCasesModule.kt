package com.futuremind.loyaltyrewards.di

import com.futuremind.loyaltyrewards.data.repository.RewardsRepository
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
    fun provideRewardsUseCases(
        getAvailablePoints: GetAvailablePoints,
        getRewards: GetRewards
    ) : RewardsUseCases {
        return RewardsUseCases(
            getAvailablePoints = getAvailablePoints,
            getRewards = getRewards
        )
    }
}