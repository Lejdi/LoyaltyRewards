package com.futuremind.loyaltyrewards.di

import com.futuremind.loyaltyrewards.data.repository.RewardsRepository
import com.futuremind.loyaltyrewards.domain.usecases.rewards.GetAvailablePoints
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
    fun provideRewardsUseCases(
        getAvailablePoints: GetAvailablePoints
    ) : RewardsUseCases {
        return RewardsUseCases(getAvailablePoints)
    }
}