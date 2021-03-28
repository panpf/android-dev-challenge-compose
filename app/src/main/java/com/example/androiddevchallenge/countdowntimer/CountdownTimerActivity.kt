package com.example.androiddevchallenge.countdowntimer

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.androiddevchallenge.ui.theme.MyTheme

class CountdownTimerActivity : AppCompatActivity() {

    private val viewModel by viewModels<CountdownTimerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyTheme {
                CountdownTimerUI(viewModel)
            }
        }
    }
}