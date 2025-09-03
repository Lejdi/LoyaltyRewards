package com.futuremind.loyaltyrewards.steps.common

import androidx.test.core.app.ActivityScenario.launch
import com.futuremind.loyaltyrewards.MainActivity
import com.futuremind.loyaltyrewards.steps.BaseSteps
import com.futuremind.loyaltyrewards.test.ComposeRuleHolder
import io.cucumber.java.en.When

class CommonSteps(composeRuleHolder: ComposeRuleHolder) : BaseSteps(composeRuleHolder) {

    @When("user launches the application")
    fun userLaunchesTheApplication() {
        launch(MainActivity::class.java)
    }

}