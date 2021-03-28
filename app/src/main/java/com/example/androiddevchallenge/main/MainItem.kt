package com.example.androiddevchallenge.main

import android.app.Activity
import kotlin.reflect.KClass

class MainItem(val title: String, val targetActivityClass: KClass<out Activity>? = null)