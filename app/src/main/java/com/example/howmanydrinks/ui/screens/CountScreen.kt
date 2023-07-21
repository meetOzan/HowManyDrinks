package com.example.howmanydrinks.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.howmanydrinks.ui.viewmodel.DrinkViewModel
import com.example.howmanydrinks.R
import com.example.howmanydrinks.ui.theme.HowManyDrinksTheme


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CountScreen(
    modifier: Modifier = Modifier,
    viewModel: DrinkViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val drinkMany by viewModel.number.collectAsStateWithLifecycle()
    val drinkGoal by viewModel.numberGoal.collectAsStateWithLifecycle()
    val drinkRemain by viewModel.remainingNumber.collectAsStateWithLifecycle()
    val drink by viewModel.drink.collectAsStateWithLifecycle()

    var oldCount by remember{ mutableStateOf(drinkMany) }

    SideEffect {
        oldCount = drinkMany
    }

    DrinkBar(
        maxHeight = if (drinkRemain == 0) 0.0001f else drinkRemain.toFloat(),
        height = if (drinkMany == 0) 0.0001f else drinkMany.toFloat(),
        drink.color
    )

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {

            // Slide effect used in there

            val countString = drinkMany.toString()
            val oldCountString = oldCount.toString()
            for (i in countString.indices){
                val oldChar = oldCountString.getOrNull(i)
                val newChar = countString.get(i)
                val char = if (oldChar == newChar) oldCountString.get(i) else countString.get(i)
                AnimatedContent(
                    targetState = char,
                    transitionSpec = {
                        slideInVertically { it } with slideOutVertically { -it }
                    },
                    label = "") {
                    Text(
                        text = char.toString(),
                        color = Color.Black,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 72.sp,
                        modifier = modifier.padding(vertical = 12.dp, horizontal = 4.dp)
                    )
                }
            }
            Text(
                text = "/",
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                fontSize = 72.sp,
                modifier = modifier.padding(vertical = 12.dp)
            )
            Text(
                text = drinkGoal.toString(),
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                fontSize = 72.sp,
                modifier = modifier.padding(vertical = 12.dp, horizontal = 4.dp)
            )
        }
        Button(
            onClick = { viewModel.increaseDrink() },
            enabled = drinkMany < drinkGoal,
            modifier = modifier.padding(vertical = 24.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSecondary),
            border = BorderStroke(1.dp, color = Color.Black),
        ) {
            Text(
                text = "Drink",
                modifier.padding(horizontal = 50.dp),
                color = Color.Black,
            )
        }

        AnimatedVisibility (drinkGoal == drinkMany) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.bon_app_tit),
                    modifier.padding(top = 24.dp, bottom = 12.dp),
                    fontFamily = FontFamily.Cursive,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSecondary,
                )
                Button(
                    onClick = { viewModel.drinkAgain() },
                    modifier = modifier.padding(vertical = 24.dp),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                ) {
                    Text(
                        text = "Go & Drink, again ?",
                        modifier.padding(horizontal = 50.dp),
                        fontFamily = FontFamily.Cursive,
                        fontSize = 24.sp,
                        color = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
fun DrinkBar(maxHeight: Float, height: Float, color: Color, modifier: Modifier = Modifier) {
    Column {
        Spacer(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White)
                .weight(maxHeight)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .background(color)
                .animateContentSize()
                .weight(height)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HowManyDrinksTheme {
        CountScreen()
    }
}