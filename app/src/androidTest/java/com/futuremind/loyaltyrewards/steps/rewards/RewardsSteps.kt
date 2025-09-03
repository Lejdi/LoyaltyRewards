package com.futuremind.loyaltyrewards.steps.rewards

import androidx.test.core.app.ActivityScenario.launch
import com.futuremind.loyaltyrewards.data.repository.MockRewardsRepository
import com.futuremind.loyaltyrewards.steps.BaseSteps
import com.futuremind.loyaltyrewards.test.ComposeRuleHolder
import com.futuremind.loyaltyrewards.MainActivity
import com.futuremind.loyaltyrewards.api.ApiReward
import com.futuremind.loyaltyrewards.api.ApiRewardActivationStatus
import io.cucumber.datatable.DataTable
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class RewardsSteps(composeRuleHolder: ComposeRuleHolder) : BaseSteps(composeRuleHolder) {

    @Given("user has {string} points available")
    fun userHasPointsAvailable(points: String) {
        MockRewardsRepository.setPoints(points.toIntOrNull() ?: 0)
    }

    @And("has has available rewards")
    fun hasHasAvailableRewards(rewards: DataTable) {
        MockRewardsRepository.clearMockedRewards()
        val mockedRewardItems = rewards.asMaps().map {
            ApiReward(
                id = it["id"]?.toInt()!!,
                name = it["name"]!!,
                coverUrl = it["image"]!!,
                pointsCost = it["cost"]?.toInt()!!
            )
        }
        val mockedRewardStatuses = rewards.asMaps().map {
            ApiRewardActivationStatus(
                id = it["id"]?.toInt()!!,
                activatedCount = it["activatedCount"]?.toInt()!!
            )
        }
        MockRewardsRepository.setRewards(
            mockedRewards = mockedRewardItems,
            mockedRewardsStatuses = mockedRewardStatuses
        )
    }

    @When("user launches the application")
    fun userLaunchesTheApplication() {
        launch(MainActivity::class.java)
    }

    @Then("available points section is visible with value of {string}")
    fun availablePointsSectionIsVisibleWithValueOf(value: String) {

    }

    @And("rewards section is visible with rewards")
    fun rewardsSectionIsVisibleWithRewards(rewards: DataTable) {

    }
}