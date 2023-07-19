package com.example.howmanydrinks

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi

@Composable
fun SelectDrink(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            LazyRow(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(drinkList) {
                    Column(
                        modifier = modifier
                            .border(
                                1.2.dp, color = it.color, shape = MaterialTheme.shapes.medium
                            )
                            .padding(16.dp),
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

            Row {
                Button(onClick = { /*TODO*/ }) {

                }
            }

        }
    }
}


val drinkList = listOf<Drink>(
    Drink(
        "Coffee",
        Color.Green,
        "https://seeklogo.com/images/S/Starbucks_Coffee-logo-DECE0A6E4B-seeklogo.com.png"
    ),
    Drink(
        "Water",
        Color.Blue,
        "https://i.pinimg.com/originals/36/d9/ae/36d9aeea2bdb1d5e2c9da1f5a2692447.png"
    ),
    Drink(
        "Tea",
        Color.Red,
        "https://allinonelondon.com/cdn/shop/products/Glassshop1_17_828x700.png?v=1655824235"
    ),
)

@Preview
@Composable
fun PrevSelectDrink() {
    SelectDrink()
}
    

