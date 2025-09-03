package com.futuremind.loyaltyrewards.test

import androidx.annotation.DrawableRes
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.SemanticsNodeInteractionCollection
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onSiblings
import com.futuremind.loyaltyrewards.presentation.common.components.ImageResourceId

fun SemanticsNodeInteraction.checkImage(@DrawableRes iconId: Int) =
    assertHasImage(iconId)


private fun SemanticsNodeInteraction.assertHasImage(imageResourceId: Int): SemanticsNodeInteraction = assert(hasImage(imageResourceId))

fun hasImage(@DrawableRes imageResourceId: Int) = SemanticsMatcher("image resource == $imageResourceId") {
    it.config.getOrNull(ImageResourceId) == imageResourceId
}

fun SemanticsNodeInteractionCollection.singleWithTag(tag: String) = filterToOne(hasTestTag(tag))

fun SemanticsNodeInteractionCollection.singleWithText(text: String) = filterToOne(hasAnyChild(hasText(text)))

fun SemanticsNodeInteraction.onChildWithTag(testTag: String): SemanticsNodeInteraction {
    return onChildren().singleWithTag(testTag)
}

fun SemanticsNodeInteraction.onChildWithText(text: String): SemanticsNodeInteraction {
    return onChildren().singleWithText(text)
}

fun SemanticsNodeInteraction.onSiblingWithTag(testTag: String): SemanticsNodeInteraction {
    return onSiblings().singleWithTag(testTag)
}