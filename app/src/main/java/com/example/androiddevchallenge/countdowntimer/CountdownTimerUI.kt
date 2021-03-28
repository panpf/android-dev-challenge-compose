package com.example.androiddevchallenge.countdowntimer

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun CountdownTimerUI(viewModel: CountdownTimerViewModel) {
    val running: Boolean by viewModel.runningLiveData.observeAsState(false)
    if (!running) {
        StartUI(viewModel)
    } else {
        RunningUI(viewModel)
    }
}

@Composable
fun StartUI(viewModel: CountdownTimerViewModel) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val hourValue: Int by viewModel.hourValueLiveData.observeAsState(0)
        val minuteValue: Int by viewModel.minuteValueLiveData.observeAsState(0)
        val secondValue: Int by viewModel.secondValueLiveData.observeAsState(0)
        Row(Modifier.wrapContentSize()) {
            Column(Modifier.size(60.dp, 150.dp)) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .border(1.dp, Color.LightGray)
                        .clickable {
                            viewModel.hourValueLiveData.postValue((hourValue + 1) % 10)
                        }
                ) {
                    Text(
                        text = "+",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(
                        text = hourValue.toString(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .border(1.dp, Color.LightGray)
                        .clickable {
                            viewModel.hourValueLiveData.postValue(0.coerceAtLeast(hourValue - 1))
                        }
                ) {
                    Text(
                        text = "-",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Column(Modifier.size(40.dp, 150.dp)) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                )
                Box(
                    Modifier
                        .width(40.dp)
                        .height(60.dp)
                ) {
                    Text(
                        text = "小时",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                )
            }

            Column(Modifier.size(60.dp, 150.dp)) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .border(1.dp, Color.LightGray)
                        .clickable {
                            viewModel.minuteValueLiveData.postValue((minuteValue + 1) % 10)
                        }
                ) {
                    Text(
                        text = "+",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(
                        text = minuteValue.toString(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .border(1.dp, Color.LightGray)
                        .clickable {
                            viewModel.minuteValueLiveData.postValue(0.coerceAtLeast(minuteValue - 1))
                        }
                ) {
                    Text(
                        text = "-",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Column(Modifier.size(40.dp, 150.dp)) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                )
                Box(
                    Modifier
                        .width(40.dp)
                        .height(60.dp)
                ) {
                    Text(
                        text = "分钟",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                )
            }

            Column(Modifier.size(60.dp, 150.dp)) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .border(1.dp, Color.LightGray)
                        .clickable {
                            viewModel.secondValueLiveData.postValue((secondValue + 1) % 10)
                        }
                ) {
                    Text(
                        text = "+",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(
                        text = secondValue.toString(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .border(1.dp, Color.LightGray)
                        .clickable {
                            viewModel.secondValueLiveData.postValue(0.coerceAtLeast(secondValue - 1))
                        }
                ) {
                    Text(
                        text = "-",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Column(Modifier.size(40.dp, 150.dp)) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                )
                Box(
                    Modifier
                        .width(40.dp)
                        .height(60.dp)
                ) {
                    Text(
                        text = "秒",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                )
            }
        }

        Spacer(modifier = Modifier.size(100.dp, 50.dp))

        Button(
            modifier = Modifier
                .size(100.dp, 40.dp)
                .align(Alignment.CenterHorizontally),
            enabled = hourValue != 0 || minuteValue != 0 || secondValue != 0,
            onClick = { /*TODO*/ }
        ) {
            Text(text = "开始")
        }
    }
}

@Preview
@Composable
fun StartUIPreview() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            StartUI(CountdownTimerViewModel())
        }
    }
}

@Composable
fun RunningUI(viewModel: CountdownTimerViewModel) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Button(modifier = Modifier.size(100.dp, 40.dp), onClick = { /*TODO*/ }) {
                Text(text = "暂停")
            }
            Spacer(modifier = Modifier.size(20.dp, 20.dp))
            Button(modifier = Modifier.size(100.dp, 40.dp), onClick = { /*TODO*/ }) {
                Text(text = "停止")
            }
        }
    }
}

@Preview
@Composable
fun RunningUIPreview() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            RunningUI(CountdownTimerViewModel())
        }
    }
}