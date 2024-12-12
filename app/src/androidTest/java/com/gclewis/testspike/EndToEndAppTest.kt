package com.gclewis.testspike

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.gclewis.testspike.ui.theme.TestSpikeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EndToEndAppTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeRule.activity.setContent {
            TestSpikeTheme {
                Navigation()
            }
        }
    }

    @Test
    fun endToEndTest() {
        val context = composeRule.activity.applicationContext

        composeRule
            .onNodeWithText(context.getString(R.string.first_screen_description))
            .assertIsDisplayed()
        composeRule
            .onNodeWithText(context.getString(R.string.navigate_to_second_screen))
            .performClick()
        composeRule
            .onNodeWithText(context.getString(R.string.second_screen_description))
            .assertIsDisplayed()
        composeRule
            .onNodeWithText(context.getString(R.string.navigate_to_third_screen))
            .performClick()
        composeRule
            .onNodeWithText(context.getString(R.string.third_screen_description))
            .assertIsDisplayed()
    }

    @Test
    fun endToEndShowHideDetailsTest() {
        val context = composeRule.activity.applicationContext

        composeRule
            .onNodeWithText(context.getString(R.string.navigate_to_second_screen))
            .performClick()
        composeRule
            .onNodeWithText(context.getString(R.string.second_screen_detail_text))
            .assertDoesNotExist()

        composeRule
            .onNodeWithText(context.getString(R.string.button_show_details))
            .performClick()

        composeRule
            .onNodeWithText(context.getString(R.string.second_screen_detail_text))
            .assertIsDisplayed()

        composeRule
            .onNodeWithText(context.getString(R.string.button_show_details))
            .performClick()

        composeRule
            .onNodeWithText(context.getString(R.string.second_screen_detail_text))
            .assertDoesNotExist()
    }
}
