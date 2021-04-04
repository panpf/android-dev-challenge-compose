package com.example.dogadopt.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import com.example.dogadopt.ui.theme.MyTheme
import java.util.*

class DogListActivity : AppCompatActivity() {

    private val viewModel by viewModels<DogListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.dogListLiveData.observe(this) { dogList ->
            dogList ?: return@observe
            setContent {
                MyTheme {
                    DogList(dogList) { dog ->
                        startActivity(
                            Intent(this@DogListActivity, DogDetailActivity::class.java).apply {
                                putExtra("dog", dog)
                            }
                        )
                    }
                }
            }
        }
    }
}