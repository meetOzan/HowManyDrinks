package com.example.howmanydrinks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.howmanydrinks.screens.CountScreen
import com.example.howmanydrinks.screens.SelectDrink
import com.example.howmanydrinks.ui.theme.HowManyDrinksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HowManyDrinksTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    viewModel: DrinkViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val shouldNext by viewModel.isNext.collectAsStateWithLifecycle()
    val drinkName by viewModel.drink.collectAsStateWithLifecycle()
    val drinkGoal by viewModel.numberGoal.collectAsStateWithLifecycle()

    Surface(modifier) {
        if (!shouldNext) {
            CountScreen()
        } else {
            SelectDrink(onContinueClicked = { if (drinkName.name != "" && drinkGoal != 0) viewModel.next() })
        }
    }
}