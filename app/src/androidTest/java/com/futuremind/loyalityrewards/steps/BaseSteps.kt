package com.futuremind.loyalityrewards.steps

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.futuremind.loyalityrewards.test.ComposeRuleHolder
import dagger.hilt.android.testing.HiltAndroidTest
import io.cucumber.junit.WithJunitRule
import org.junit.Rule

@WithJunitRule(useAsTestClassInDescription = true)
@HiltAndroidTest
open class BaseSteps(
    composeRuleHolder: ComposeRuleHolder
): SemanticsNodeInteractionsProvider by composeRuleHolder.composeRule {

    @get:Rule
    val composeRule = composeRuleHolder.composeRule
}