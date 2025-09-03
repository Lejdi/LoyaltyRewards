package com.futuremind.loyaltyrewards.domain.usecases.rewards

class RewardsUseCases(
    val getAvailablePoints: GetAvailablePoints,
    val getRewards: GetRewards,
    val deactivateReward: DeactivateReward,
    val activateReward: ActivateReward,
)