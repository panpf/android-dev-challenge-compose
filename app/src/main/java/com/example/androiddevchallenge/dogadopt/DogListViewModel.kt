package com.example.androiddevchallenge.dogadopt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.R

class DogListViewModel : ViewModel() {

    val dogListLiveData = MutableLiveData<List<Dog>>()

    init {
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
        dogListLiveData.postValue(dogs)
    }
}