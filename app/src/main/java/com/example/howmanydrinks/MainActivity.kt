package com.example.howmanydrinks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface (
        modifier = modifier.fillMaxSize()
    ){
        Column() {
            
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HowManyDrinksTheme {
    }
}