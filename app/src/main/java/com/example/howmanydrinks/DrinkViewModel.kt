package com.example.howmanydrinks

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DrinkViewModel() : ViewModel() {

    private var _number = MutableStateFlow<Int>(0)
    val number = _number.asStateFlow()

    private var _numberGoal = MutableStateFlow<Int>(0)
    val numberGoal = _numberGoal.asStateFlow()

    fun increaseDrink(){
        _number.value += 1
    }

    fun increaseGoal(){
        _numberGoal.value += 1
    }

    
}