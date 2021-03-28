package com.example.androiddevchallenge.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun MainItemListUI(itemList: List<MainItem>, callback: MainItemCallback? = null) {
    LazyColumn(Modifier.fillMaxSize()) {
        items(itemList) {
            MainItemUI(it, callback)
        }
    }
}

@Preview
@Composable
fun MainItemListUIPreview() {
    val itemList = listOf(
        MainItem("FirstWeek：Dog Adopt"),
        MainItem("SecondWeek：Countdown Timer"),
    )
    MyTheme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            MainItemListUI(itemList)
        }
    }
}

class MainItemCallback(
    val onItemClick: (item: MainItem) -> Unit
)

@Composable
fun MainItemUI(item: MainItem, callback: MainItemCallback? = null) {
    Column {
        Text(
            text = item.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clickable {
                    callback?.onItemClick?.invoke(item)
                }
                .padding(horizontal = 20.dp, vertical = 12.dp)
        )
        Divider(
            color = Color.LightGray,
            thickness = 0.5.dp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}

@Preview
@Composable
fun MainItemUIPreview() {
    val item = MainItem("FirstWeek：Dog Adopt")
    MyTheme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxWidth()) {
            MainItemUI(item)
        }
    }
}