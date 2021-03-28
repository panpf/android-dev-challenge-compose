package com.example.androiddevchallenge.dogadopt

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.MyTheme
import java.util.*


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
            .clickable {
                itemClick?.invoke(dog)
            }
            .padding(start = 20.dp, top = 14.dp, end = 20.dp, bottom = 14.dp)
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