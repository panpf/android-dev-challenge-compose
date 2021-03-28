package com.example.androiddevchallenge.dogadopt

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
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


// Start building your app here!
@Composable
fun DogDetail(dog: Dog, actionButtonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(dog.avatarResIf),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.size(20.dp))

            Text(
                text = dog.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.size(10.dp))

            Row {
                Text(text = "${dog.age} Age", fontSize = 12.sp, color = Color.Gray)
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = dog.color, fontSize = 12.sp, color = Color.Gray)
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = dog.variety, fontSize = 12.sp, color = Color.Gray)
            }

            Spacer(modifier = Modifier.size(20.dp))

            Text(text = dog.desc, lineHeight = 24.sp)

            Spacer(modifier = Modifier.size(60.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier.size(200.dp, 50.dp),
                    shape = RoundedCornerShape(25.dp),
                    onClick = actionButtonClick
                ) {
                    Text(text = "TAKE ME HOME")
                }
            }
        }
    }
}

@Composable
@Preview
fun DogDetailPreview() {
    val dog = Dog(
        "Tom",
        5,
        "White",
        "Pomeranian",
        R.drawable.dog_1,
        "This is a very well-behaved and cute Pomeranian. He is 5 years old this year. Before he was two years old, he lived a wandering life and was often bullied by other animals. Now he is eager to find a gentle and kind master!"
    )
    MyTheme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            DogDetail(dog) {
            }
        }
    }
}