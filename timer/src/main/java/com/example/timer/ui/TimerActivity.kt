package com.example.timer.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.dogadopt.ui.theme.MyTheme

class TimerActivity : AppCompatActivity() {

    private val viewModel by viewModels<TimerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyTheme {
                TimerUI(viewModel)
            }
        }
    }
}