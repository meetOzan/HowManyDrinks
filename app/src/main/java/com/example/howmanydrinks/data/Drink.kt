package com.example.howmanydrinks.data

import androidx.compose.ui.graphics.Color

data class Drink(
    var name: String,
    var color: Color,
    var url: String,
    var selected: Boolean
)