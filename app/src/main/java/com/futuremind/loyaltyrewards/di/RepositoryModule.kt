package com.futuremind.loyaltyrewards.di

import com.futuremind.loyaltyrewards.api.RewardsApi
import com.futuremind.loyaltyrewards.data.repository.ApiRewardsRepository
import com.futuremind.loyaltyrewards.data.repository.RewardsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRewardsRepository(
        api: RewardsApi
    ) : RewardsRepository {
        return ApiRewardsRepository(api)
    }
}