package com.example.androiddevchallenge.countdowntimer

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.utils.DateUtils

class CountdownTimerUICallback(
    val onHourAdd: ((Int) -> Unit)? = null,
    val onMinuteAdd: ((Int) -> Unit)? = null,
    val onSecondAdd: ((Int) -> Unit)? = null,
    val onClickStart: (() -> Unit)? = null,
    val onClickReset: (() -> Unit)? = null,
    val onClickStop: (() -> Unit)? = null,
    val onClickPause: (() -> Unit)? = null,
    val onClickResume: (() -> Unit)? = null,
    val onClickIKnown: (() -> Unit)? = null,
)

@Composable
fun CountdownTimerUI(viewModel: CountdownTimerViewModel) {
    val timerState: TimerState by viewModel.timerStateLiveData.observeAsState(TimerState.NONE)

    when (timerState) {
        TimerState.NONE -> {
            StartUI(viewModel)
        }
        TimerState.RUNNING, TimerState.PAUSED -> {
            RunningUI(viewModel)
        }
        TimerState.COMPLETED -> {
            CompletedUI(viewModel)
        }
    }
}

@Composable
fun StartUI(viewModel: CountdownTimerViewModel) {
    val hourValue: Int by viewModel.hourValueLiveData.observeAsState(0)
    val minuteValue: Int by viewModel.minuteValueLiveData.observeAsState(0)
    val secondValue: Int by viewModel.secondValueLiveData.observeAsState(0)

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp)

        Row(Modifier.wrapContentSize()) {
            Column(Modifier.size(60.dp, 150.dp)) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .border(1.dp, Color.LightGray)
                        .clickable { viewModel.callback.onHourAdd?.invoke(1) }
                ) {
                    Text("+", Modifier.align(Alignment.Center))
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(
                        text = String.format("%02d", hourValue),
                        modifier = Modifier.align(Alignment.Center),
                        style = textStyle
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .border(1.dp, Color.LightGray)
                        .clickable { viewModel.callback.onHourAdd?.invoke(-1) }
                ) {
                    Text(text = "-", modifier = Modifier.align(Alignment.Center))
                }
            }

            Column(Modifier.size(20.dp, 150.dp)) {
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
                        text = ":",
                        modifier = Modifier.align(Alignment.Center),
                        style = textStyle
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
                        .clickable { viewModel.callback.onMinuteAdd?.invoke(1) }
                ) {
                    Text(text = "+", modifier = Modifier.align(Alignment.Center))
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(
                        text = String.format("%02d", minuteValue),
                        modifier = Modifier.align(Alignment.Center),
                        style = textStyle
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .border(1.dp, Color.LightGray)
                        .clickable { viewModel.callback.onMinuteAdd?.invoke(-1) }
                ) {
                    Text(text = "-", modifier = Modifier.align(Alignment.Center))
                }
            }

            Column(Modifier.size(20.dp, 150.dp)) {
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
                        text = ":",
                        modifier = Modifier.align(Alignment.Center),
                        style = textStyle
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
                        .clickable { viewModel.callback.onSecondAdd?.invoke(1) }
                ) {
                    Text(text = "+", modifier = Modifier.align(Alignment.Center))
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(
                        text = String.format("%02d", secondValue),
                        modifier = Modifier.align(Alignment.Center),
                        style = textStyle
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .border(1.dp, Color.LightGray)
                        .clickable { viewModel.callback.onSecondAdd?.invoke(-1) }
                ) {
                    Text(text = "-", modifier = Modifier.align(Alignment.Center))
                }
            }
        }

        Spacer(modifier = Modifier.size(50.dp, 50.dp))

        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Button(
                modifier = Modifier.size(100.dp, 40.dp),
                onClick = { viewModel.callback.onClickReset?.invoke() },
                enabled = hourValue != 0 || minuteValue != 0 || secondValue != 0,
            ) {
                Text(text = "RESET")
            }

            Spacer(modifier = Modifier.size(20.dp, 20.dp))

            Button(
                modifier = Modifier.size(100.dp, 40.dp),
                onClick = { viewModel.callback.onClickStart?.invoke() },
                enabled = hourValue != 0 || minuteValue != 0 || secondValue != 0,
            ) {
                Text(text = "START")
            }
        }
    }
}

@Composable
fun RunningUI(viewModel: CountdownTimerViewModel) {
    val leftMillisecond: Long by viewModel.leftMillisecondLiveData.observeAsState(0L)
    val timerState: TimerState by viewModel.timerStateLiveData.observeAsState(TimerState.NONE)

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = DateUtils.formatTimeLength(leftMillisecond, "%H:%M:%S:%MS"),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.size(50.dp, 50.dp))

        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Button(
                modifier = Modifier.size(100.dp, 40.dp),
                onClick = { viewModel.callback.onClickStop?.invoke() }) {
                Text(text = "STOP")
            }

            Spacer(modifier = Modifier.size(20.dp, 20.dp))

            if (timerState == TimerState.RUNNING) {
                Button(
                    modifier = Modifier.size(100.dp, 40.dp),
                    onClick = { viewModel.callback.onClickPause?.invoke() }) {
                    Text(text = "PAUSE")
                }
            } else {
                Button(
                    modifier = Modifier.size(100.dp, 40.dp),
                    onClick = { viewModel.callback.onClickResume?.invoke() }) {
                    Text(text = "RESUME")
                }
            }
        }
    }
}

@Composable
fun CompletedUI(viewModel: CountdownTimerViewModel) {
    val totalMillisecond: Long by viewModel.totalMillisecondLiveData.observeAsState(0L)

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_alarm),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
            modifier = Modifier.size(100.dp, 100.dp)
        )

        Spacer(modifier = Modifier.size(20.dp, 20.dp))

        Text(
            text = "Countdown ends!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.size(10.dp, 10.dp))

        Text(text = DateUtils.formatTimeLength(totalMillisecond, "%h?h %m?m %s?s"))

        Spacer(modifier = Modifier.size(50.dp, 50.dp))

        Button(
            modifier = Modifier.size(100.dp, 40.dp),
            onClick = { viewModel.callback.onClickIKnown?.invoke() }) {
            Text(text = "I KNOWN")
        }
    }
}

@Preview
@Composable
fun StartUIPreview() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background) {
            StartUI(CountdownTimerViewModel())
        }
    }
}

@Preview
@Composable
fun RunningUIPreview() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background) {
            RunningUI(CountdownTimerViewModel())
        }
    }
}

@Preview
@Composable
fun CompletedUIPreview() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background) {
            CompletedUI(CountdownTimerViewModel())
        }
    }
}