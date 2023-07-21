package com.example.howmanydrinks.ui.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.howmanydrinks.data.Drink
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DrinkViewModel : ViewModel() {

    private var _number = MutableStateFlow(0)
    val number = _number.asStateFlow()

    private var _numberGoal = MutableStateFlow(0)
    val numberGoal = _numberGoal.asStateFlow()

    private var _remainingNumber = MutableStateFlow(0)
    val remainingNumber = _remainingNumber.asStateFlow()

    private var _drink = MutableStateFlow<Drink>(Drink("", Color.White, "", false))
    val drink = _drink.asStateFlow()

    private var _isNext = MutableStateFlow(true)
    val isNext = _isNext.asStateFlow()

    fun increaseDrink() {
        _number.value += 1
        _remainingNumber.value -= 1
    }

    fun increaseGoal() {
        _numberGoal.value += 1
        _remainingNumber.value += 1
    }

    fun reduceGoal() {
        _numberGoal.value -= 1
        _remainingNumber.value += 1
    }

    fun chooseDrink(newDrink: Drink) {
        _drink.value = newDrink
    }

    fun next() {
        _isNext.value = !_isNext.value
    }

    fun drinkAgain() {
        _isNext.value = true
        _numberGoal.value = 0
        _remainingNumber.value = 0
        _number.value = 0
    }

}