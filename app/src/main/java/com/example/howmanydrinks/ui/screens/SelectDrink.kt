package com.example.howmanydrinks.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.howmanydrinks.data.Drink
import com.example.howmanydrinks.ui.viewmodel.DrinkViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun SelectDrink(
    modifier: Modifier = Modifier,
    onContinueClicked: () -> Unit,
    viewModel: DrinkViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val drinkList = remember {
        mutableStateListOf(
            Drink(
                "Coffee",
                Color(0xFF662F0A),
                "https://seeklogo.com/images/S/Starbucks_Coffee-logo-DECE0A6E4B-seeklogo.com.png",
                false
            ), Drink(
                "Water",
                Color.Blue,
                "https://i.pinimg.com/originals/36/d9/ae/36d9aeea2bdb1d5e2c9da1f5a2692447.png",
                false
            ), Drink(
                "Tea",
                Color.Red,
                "https://allinonelondon.com/cdn/shop/products/Glassshop1_17_828x700.png?v=1655824235",
                false
            )
        )
    }

    val numberGoal by viewModel.numberGoal.collectAsStateWithLifecycle()
    val drinkName by viewModel.drink.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color.LightGray, Color.White))),
        verticalArrangement = Arrangement.spacedBy(72.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "What's your drink ?",
            modifier.padding(16.dp),
            style = MaterialTheme.typography.displayMedium,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center
        )
        AnimatedVisibility(
            visible = drinkName.name != "",
            enter = slideInVertically(
                tween(
                    durationMillis = 150,
                    easing = LinearOutSlowInEasing
                )
            )
        ) {
            Text(
                text = drinkName.name,
                style = MaterialTheme.typography.displaySmall,
                color = drinkName.color,
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.Center
            )
        }
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(drinkList) {
                Surface(
                    onClick = { viewModel.chooseDrink(it) },
                    modifier.padding(4.dp),
                    shape = MaterialTheme.shapes.large
                ) {
                    Column(
                        modifier = modifier
                            .border(
                                1.2.dp, color = it.color, shape = MaterialTheme.shapes.large
                            )
                            .padding(10.dp)
                            .background(if (it.selected) Color.Red else Color.White),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = it.url,
                            contentDescription = it.name,
                            modifier
                                .padding(vertical = 8.dp)
                                .size(72.dp)
                        )
                        Text(
                            text = it.name, modifier.padding(8.dp), color = Color.Black
                        )
                    }
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = { viewModel.reduceGoal() },
                    modifier = modifier.size(40.dp),
                    colors = IconButtonDefaults.iconButtonColors(Color(0xE4B596CA)),
                    enabled = numberGoal > 0
                ) {
                    Icon(
                        imageVector = Icons.Filled.Remove, contentDescription = "minus"
                    )
                }

                // Slide effect used in there

                var oldCount by remember { mutableStateOf(numberGoal) }

                SideEffect {
                    oldCount = numberGoal
                }

                val countString = numberGoal.toString()
                val oldCountString = oldCount.toString()
                for (i in countString.indices) {
                    val oldChar = oldCountString.getOrNull(i)
                    val newChar = countString.get(i)
                    val char = if (oldChar == newChar) oldCountString.get(i) else countString.get(i)
                    AnimatedContent(
                        targetState = char,
                        transitionSpec = {
                            slideInVertically { it } with slideOutVertically { -it }
                        },
                        label = ""
                    ) {
                        Text(
                            text = numberGoal.toString(),
                            fontSize = 46.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily.Serif
                        )
                    }
                    IconButton(
                        onClick = { viewModel.increaseGoal() },
                        colors = IconButtonDefaults.iconButtonColors(Color(0xE89F65C7)),
                        modifier = modifier.size(40.dp),
                        enabled = numberGoal < 10
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add, contentDescription = "plus"
                        )
                    }
                }
            }
            ElevatedButton(
                onClick = onContinueClicked,
                colors = ButtonDefaults.elevatedButtonColors(
                    Color.LightGray
                ),
                border = BorderStroke(1.dp, color = Color.Black),
                modifier = modifier
                    .width(160.dp)
                    .padding(top = 36.dp)
            ) {
                androidx.compose.material3.Text(
                    text = "Continue",
                    color = Color.Black,
                )
            }
        }
    }
}