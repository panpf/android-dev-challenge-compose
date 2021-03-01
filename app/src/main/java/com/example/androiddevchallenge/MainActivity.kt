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
import android.os.Parcelable
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlinx.parcelize.Parcelize
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dogs = listOf(
            Dog("Tom", 5, "White", "Pomeranian", R.drawable.dog_1),
            Dog("Lemon", 2, "Gray", "Chinese Pastoral Dog", R.drawable.dog_2),
            Dog("Friendlies", 1, "White Black", "Dalmatian", R.drawable.dog_3),
            Dog("Marshall", 4, "Black Yellow", "Poodle", R.drawable.dog_4),
            Dog("Beulah", 1, "White", "Pomeranian", R.drawable.dog_5),
            Dog("Rick", 2, "Yellow", "Chinese Pastoral Dog", R.drawable.dog_6),
            Dog("Isabella", 3, "Yellow", "Pomeranian", R.drawable.dog_7),
            Dog("Constance", 2, "Yellow", "Chinese Pastoral Dog", R.drawable.dog_8),
            Dog("Missyou", 4, "White", "Chinese Pastoral Dog", R.drawable.dog_9)
        )
        setContent {
            MyTheme {
                DogList(dogs) { dog ->
                    startActivity(
                        Intent(this@MainActivity, DetailActivity::class.java).apply {
                            putExtra("dog", dog)
                        }
                    )
                }
            }
        }
    }
}

// Start building your app here!
@Composable
fun DogList(dogs: List<Dog>, itemClick: ((Dog) -> Unit)? = null) {
    LazyColumn {
        items(dogs) { dog ->
            DogItem(dog, itemClick)
        }
    }
}

@Composable
@Preview
fun DogListPreview() {
    val dogs = ArrayList<Dog>().apply {
        repeat(30) {
            add(Dog("Tom", 5, "White", "Pomeranian", R.drawable.dog_1))
        }
    }
    MyTheme {
        Surface(color = MaterialTheme.colors.background) {
            DogList(dogs)
        }
    }
}

@Composable
fun DogItem(dog: Dog, itemClick: ((Dog) -> Unit)? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 14.dp, end = 20.dp, bottom = 14.dp)
            .clickable {
                itemClick?.invoke(dog)
            }
    ) {
        Image(
            painter = painterResource(dog.avatarResIf),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.size(10.dp))
        Column {
            Text(
                text = dog.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Row {
                Text(text = "${dog.age} Age", fontSize = 12.sp, color = Color.Gray)
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = dog.color, fontSize = 12.sp, color = Color.Gray)
            }
            Text(text = dog.variety, fontSize = 13.sp)
        }
    }
}

@Composable
@Preview
fun DogItemPreview() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            DogItem(Dog("Tom", 5, "White", "Pomeranian", R.drawable.dog_1))
        }
    }
}

@Parcelize
class Dog(
    val name: String,
    val age: Int,
    val color: String,
    val variety: String,
    val avatarResIf: Int,
    val desc: String = "This is a very well-behaved and cute $variety. He is $age years old this year. Before that, he lived a wandering life and was often bullied by other animals. Now he is eager to find a gentle and kind master!"
) : Parcelable
