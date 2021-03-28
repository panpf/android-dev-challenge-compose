/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import com.example.androiddevchallenge.countdowntimer.CountdownTimerActivity
import com.example.androiddevchallenge.dogadopt.DogListActivity
import com.example.androiddevchallenge.main.MainItem
import com.example.androiddevchallenge.main.MainItemCallback
import com.example.androiddevchallenge.main.MainItemListUI
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val itemList = listOf(
            MainItem("FirstWeek：Dog Adopt", DogListActivity::class),
            MainItem("SecondWeek：Countdown Timer", CountdownTimerActivity::class),
        )
        setContent {
            MyTheme {
                MainItemListUI(itemList, MainItemCallback(
                    onItemClick = {
                        if (it.targetActivityClass != null) {
                            startActivity(
                                Intent(this@MainActivity, it.targetActivityClass.java)
                            )
                        }
                    }
                ))
            }
        }
    }
}
