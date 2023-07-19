package com.example.howmanydrinks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.howmanydrinks.ui.theme.HowManyDrinksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HowManyDrinksTheme {

            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    text: Int,
    viewModel: DrinkViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val drinkMany by viewModel.number.collectAsStateWithLifecycle()

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Text(
                    text = drinkMany.toString(),
                    color = Color.Black,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 72.sp,
                    modifier = modifier.padding(vertical = 12.dp, horizontal = 4.dp)
                )
                Text(
                    text = "/",
                    color = Color.Black,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 72.sp,
                    modifier = modifier.padding(vertical = 12.dp)
                )
                Text(
                    text = text.toString(),
                    color = Color.Black,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 72.sp,
                    modifier = modifier.padding(vertical = 12.dp, horizontal = 4.dp)
                )
            }
            ElevatedButton(
                onClick = { viewModel.increaseDrink() },
                colors = ButtonDefaults.elevatedButtonColors(
                    Color.White
                ),
                border = BorderStroke(1.dp, color = Color.Black),
                enabled = drinkMany < text,
                modifier = modifier
                    .width(180.dp)
                    .padding(vertical = 24.dp)
            ) {
                Text(
                    text = "Drink",
                    color = Color.Black,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HowManyDrinksTheme {
        MainScreen(text = 3)
    }
}