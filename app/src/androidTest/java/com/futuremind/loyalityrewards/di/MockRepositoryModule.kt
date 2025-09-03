package com.futuremind.loyalityrewards.di

import com.futuremind.loyalityrewards.data.repository.MockRewardsRepository
import com.futuremind.loyaltyrewards.data.repository.RewardsRepository
import com.futuremind.loyaltyrewards.di.RepositoryModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
object MockRepositoryModule {

    @Singleton
    @Provides
    fun provideRewardsRepository() : RewardsRepository {
        return MockRewardsRepository
    }

}