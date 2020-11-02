package com.d.guessnum

import android.content.res.Resources
import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
//@LargeTest
//當你會用到網路存取資料、資料庫、多執行緒等等較花費時間，在你的測試類別上方加上@LargeTest
//測試執行時間會超過1秒的，一般都會被歸為LargeTest。
class MaterialActivityTest {

    @Rule
    @JvmField
    //開啟被測試的Activity
    val activityRule = ActivityScenarioRule<MaterialActivity>(MaterialActivity::class.java)

    @Test
    fun guessWrong(){
        var secret = 0
        lateinit var resources:Resources

        activityRule.scenario.onActivity { activity ->
            secret = activity.secretNumber.secret
            resources = activity.resources
            Log.d("TEST", "secret: $secret")
        }

        for (n in 1..10){
            if(n != secret){
                onView(withId(R.id.ed_number)).perform(clearText())
                onView(withId(R.id.ed_number)).perform(typeText(n.toString()))
                onView(withId(R.id.button_ok)).perform(click())
                val message =
                    if(n < secret) resources.getString(R.string.bigger)
                    else resources.getString(R.string.smaller)
                //取得是否有文字為message的元件存在
                onView(withText(message)).check(matches(isDisplayed()))
                onView(withText(resources.getString(R.string.ok))).perform(click())
            }
        }
    }

    @Test
    fun counterReset(){
        var secret = 0
        var counter = ""
        lateinit var resources:Resources

        activityRule.scenario.onActivity { activity ->
            secret = activity.secretNumber.secret
            resources = activity.resources
            Log.d("TEST", "secret: $secret")
        }

        //play five times
        for (n in 1..5){
            if(n != secret){
                onView(withId(R.id.ed_number)).perform(clearText())
                onView(withId(R.id.ed_number)).perform(typeText(n.toString()))
                onView(withId(R.id.button_ok)).perform(click())
                onView(withText(resources.getString(R.string.ok))).perform(click(),closeSoftKeyboard())

                //check counter reset
                onView(withId(R.id.fab)).perform(click())
                onView(withText(resources.getString(R.string.ok))).perform(click())
                onView(withId(R.id.counter)).check(matches(withText("0")))
            }
        }
    }
    //其他驗證的方式
    //
    //    text is：檢查文字內容是否是該文字
    //    exists：檢查View元件是存在於於螢幕可見的View中。
    //    does not exist：檢查View元件是不存在於於螢幕可見的View中。
}