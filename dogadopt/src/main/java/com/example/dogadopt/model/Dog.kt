package com.example.dogadopt.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class Dog(
    val name: String,
    val age: Int,
    val color: String,
    val variety: String,
    val avatarResIf: Int,
    val desc: String = "This is a very well-behaved and cute $variety. He is $age years old this year. Before that, he lived a wandering life and was often bullied by other animals. Now he is eager to find a gentle and kind master!"
) : Parcelable