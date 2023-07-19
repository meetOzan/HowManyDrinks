package com.example.howmanydrinks

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DrinkViewModel() : ViewModel() {

    private var _number = MutableStateFlow(0)
    val number = _number.asStateFlow()

    private var _numberGoal = MutableStateFlow(0)
    val numberGoal = _numberGoal.asStateFlow()

    private var _drinkName = MutableStateFlow<Drink>(Drink("", Color.White,"",false))
    val drinkName = _drinkName.asStateFlow()

    fun increaseDrink(){
        _number.value += 1
    }

    fun increaseGoal(){
        _numberGoal.value += 1
    }

    fun reduceGoal(){
        _numberGoal.value -= 1
    }

    fun chooseDrink(newDrink : Drink){
        _drinkName.value = newDrink
    }
    
}