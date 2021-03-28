package com.example.androiddevchallenge.countdowntimer

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.androiddevchallenge.ui.theme.MyTheme

class CountdownTimerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = CountdownTimerViewModel()
        setContent {
            MyTheme {
                CountdownTimerUI(viewModel)
            }
        }
    }
}