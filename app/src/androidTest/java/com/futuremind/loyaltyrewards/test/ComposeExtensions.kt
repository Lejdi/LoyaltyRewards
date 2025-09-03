package com.futuremind.loyaltyrewards.test

import androidx.annotation.DrawableRes
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.SemanticsNodeInteractionCollection
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onChildren
import com.futuremind.loyaltyrewards.presentation.common.components.ImageResourceId

fun SemanticsNodeInteraction.checkImage(@DrawableRes iconId: Int) =
    assertHasImage(iconId)


private fun SemanticsNodeInteraction.assertHasImage(imageResourceId: Int): SemanticsNodeInteraction = assert(hasImage(imageResourceId))

fun hasImage(@DrawableRes imageResourceId: Int) = SemanticsMatcher("image resource == $imageResourceId") {
    it.config.getOrNull(ImageResourceId) == imageResourceId
}

fun SemanticsNodeInteractionCollection.singleWithTag(tag: String) = filterToOne(hasTestTag(tag))

fun SemanticsNodeInteraction.onChildWithTag(testTag: String): SemanticsNodeInteraction {
    return onChildren().singleWithTag(testTag)
}