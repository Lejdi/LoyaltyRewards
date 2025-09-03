package com.futuremind.loyaltyrewards.steps.rewards

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.core.app.ActivityScenario.launch
import com.futuremind.loyaltyrewards.MainActivity
import com.futuremind.loyaltyrewards.R
import com.futuremind.loyaltyrewards.api.ApiReward
import com.futuremind.loyaltyrewards.api.ApiRewardActivationStatus
import com.futuremind.loyaltyrewards.data.repository.MockRewardsRepository
import com.futuremind.loyaltyrewards.presentation.rewards.ui.PointsSectionTestTags
import com.futuremind.loyaltyrewards.presentation.rewards.ui.rewardsrow.RewardsRowRewardButtonTestTags
import com.futuremind.loyaltyrewards.presentation.rewards.ui.rewardsrow.RewardsRowRewardCardTestTags
import com.futuremind.loyaltyrewards.presentation.rewards.ui.rewardsrow.RewardsRowRewardNameTestTags
import com.futuremind.loyaltyrewards.presentation.rewards.ui.rewardsrow.RewardsRowTestTags
import com.futuremind.loyaltyrewards.steps.BaseSteps
import com.futuremind.loyaltyrewards.test.ComposeRuleHolder
import com.futuremind.loyaltyrewards.test.checkImage
import com.futuremind.loyaltyrewards.test.onChildWithTag
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
                coverUrl = "",
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
        onNodeWithTag(PointsSectionTestTags.REWARDS_POINTS_SECTION_VALUE_TAG)
            .assertTextEquals(value)
    }

    @And("rewards section is visible with rewards")
    fun rewardsSectionIsVisibleWithRewards(rewards: DataTable) {
        rewards.asMaps().forEachIndexed { index, entries ->
            onNodeWithTag(RewardsRowTestTags.REWARDS_ROW_TAG).performScrollToIndex(index)
            val name = entries["name"]!!
            val image = entries["image"]!!
            val cost = entries["cost"]!!
            val activated = entries["activated"] == "yes"
            val available = entries["available"] == "yes"

            onNodeWithTag(RewardsRowTestTags.REWARDS_ROW_TAG, true) //rewards row
                .onChildren().filterToOne(hasAnyChild(hasText(name))).apply { //rewards card
                    if (image == "placeholder") {
                        onChildWithTag(RewardsRowRewardCardTestTags.REWARDS_ROW_REWARD_CARD_PLACEHOLDER_TAG) //placeholder
                            .assertIsDisplayed()
                    }
                    onChildWithTag(RewardsRowRewardNameTestTags.REWARDS_ROW_REWARD_NAME_TAG) //name
                        .assertTextEquals(name)

                    onChildWithTag(RewardsRowRewardButtonTestTags.REWARDS_ROW_REWARD_BUTTON_TAG).apply { //button
                        onChildWithTag(RewardsRowRewardButtonTestTags.REWARDS_ROW_REWARD_BUTTON_PRICE_TAG) //button price text
                            .assertTextEquals(cost)

                        val imageRes = if(activated) R.drawable.ic_check
                        else {
                            if(available) R.drawable.ic_unlock
                            else R.drawable.ic_lock
                        }

                        onChildWithTag(RewardsRowRewardButtonTestTags.REWARDS_ROW_REWARD_BUTTON_ICON_TAG) //button image
                            .checkImage(imageRes)
                    }
                }
        }
    }
}