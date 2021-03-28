package com.example.androiddevchallenge.countdowntimer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountdownTimerViewModel : ViewModel() {
    var runningLiveData = MutableLiveData(false)
    var hourValueLiveData = MutableLiveData(0)
    var minuteValueLiveData = MutableLiveData(0)
    var secondValueLiveData = MutableLiveData(0)
}