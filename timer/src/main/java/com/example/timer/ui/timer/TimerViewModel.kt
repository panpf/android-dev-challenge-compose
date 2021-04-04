package com.example.timer.ui.timer

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {

    var timerStateLiveData = MutableLiveData(TimerState.NONE)
    var hourValueLiveData = MutableLiveData(0)
    var minuteValueLiveData = MutableLiveData(0)
    var secondValueLiveData = MutableLiveData(0)
    var leftMillisecondLiveData = MutableLiveData(0L)
    var totalMillisecondLiveData = MutableLiveData(0L)

    private var valueAnimator: ValueAnimator? = null

    val callback = TimerUICallback(
        onHourAdd = {
            hourValueLiveData.postValue(((hourValueLiveData.value ?: 0) + it).coerceAtLeast(0))
        },
        onMinuteAdd = {
            minuteValueLiveData.postValue(
                ((minuteValueLiveData.value ?: 0) + it).coerceAtLeast(0) % 60
            )
        },
        onSecondAdd = {
            secondValueLiveData.postValue(
                ((secondValueLiveData.value ?: 0) + it).coerceAtLeast(0) % 60
            )
        },
        onClickStart = {
            val totalSecond =
                (((hourValueLiveData.value ?: 0) * 60 * 60) + ((minuteValueLiveData.value
                    ?: 0) * 60) + (secondValueLiveData.value ?: 0))
            val totalMillisecond = totalSecond * 1000L
            totalMillisecondLiveData.postValue(totalMillisecond)
            timerStateLiveData.postValue(TimerState.RUNNING)

            valueAnimator = ValueAnimator.ofObject(LongTypeEvaluator(), totalMillisecond, 0).apply {
                duration = totalMillisecond
                interpolator = LinearInterpolator()
                // Update timeLeft in ViewModel
                addUpdateListener {
                    leftMillisecondLiveData.postValue(it.animatedValue as Long)
                }
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        timerStateLiveData.postValue(TimerState.COMPLETED)
                    }
                })
                start()
            }
        },
        onClickReset = {
            hourValueLiveData.postValue(0)
            minuteValueLiveData.postValue(0)
            secondValueLiveData.postValue(0)
        },
        onClickPause = {
            valueAnimator?.pause()
            timerStateLiveData.postValue(TimerState.PAUSED)
        },
        onClickResume = {
            valueAnimator?.resume()
            timerStateLiveData.postValue(TimerState.RUNNING)
        },
        onClickStop = {
            valueAnimator?.cancel()
            valueAnimator = null
            timerStateLiveData.postValue(TimerState.NONE)
        },
        onClickIKnown = {
            valueAnimator?.cancel()
            valueAnimator = null
            timerStateLiveData.postValue(TimerState.NONE)
        }
    )

    private class LongTypeEvaluator : TypeEvaluator<Long> {
        override fun evaluate(fraction: Float, startValue: Long, endValue: Long): Long {
            return (startValue + ((endValue - startValue) * fraction)).toLong()
        }
    }
}