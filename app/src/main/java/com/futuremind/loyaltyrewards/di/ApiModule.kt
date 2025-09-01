package com.futuremind.loyaltyrewards.di

import com.futuremind.loyaltyrewards.api.RewardsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Singleton
    @Provides
    fun provideRewardsApi() = RewardsApi()


}