package com.example.button

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun RandomButtonsGame() {
    val context = LocalContext.current

    val colorSaver = Saver<Color, Int>(
        save = { it.toArgb() },
        restore = { Color(it) }
    )

    val dpSaver = Saver<Dp, Float>(
        save = { it.value },
        restore = { Dp(it) }
    )

    var button1Color by rememberSaveable(stateSaver = colorSaver) { mutableStateOf(Color.Gray) }
    var button2Color by rememberSaveable(stateSaver = colorSaver) { mutableStateOf(Color.Gray) }
    var button3Color by rememberSaveable(stateSaver = colorSaver) { mutableStateOf(Color.Gray) }

    var button1Border by rememberSaveable(stateSaver = dpSaver) { mutableStateOf(1.dp) }
    var button2Border by rememberSaveable(stateSaver = dpSaver) { mutableStateOf(1.dp) }
    var button3Border by rememberSaveable(stateSaver = dpSaver) { mutableStateOf(1.dp) }

    var button1BorderColor by rememberSaveable(stateSaver = colorSaver) { mutableStateOf(Color.Black) }
    var button2BorderColor by rememberSaveable(stateSaver = colorSaver) { mutableStateOf(Color.Black) }
    var button3BorderColor by rememberSaveable(stateSaver = colorSaver) { mutableStateOf(Color.Black) }

    fun checkWin() {
        if (button1Color == button2Color && button2Color == button3Color &&
            button1Border == button2Border && button2Border == button3Border &&
            button1BorderColor == button2BorderColor && button2BorderColor == button3BorderColor
        ) {
            Toast.makeText(context, "Победа", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                button2Color = randomColor()
                button3Color = randomColor()
                button2Border = randomBorder()
                button3Border = randomBorder()
                button2BorderColor = randomColor()
                button3BorderColor = randomColor()
                checkWin()
            },
            modifier = Modifier.padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = button1Color),
            border = BorderStroke(button1Border, button1BorderColor)
        ) {
            Text(text = "Button 1", fontSize = 18.sp)
        }
        Button(
            onClick = {
                button1Color = randomColor()
                button3Color = randomColor()
                button1Border = randomBorder()
                button3Border = randomBorder()
                button1BorderColor = randomColor()
                button3BorderColor = randomColor()
                checkWin()
            },
            modifier = Modifier.padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = button2Color),
            border = BorderStroke(button2Border, button2BorderColor)
        ) {
            Text(text = "Button 2", fontSize = 18.sp)
        }
        Button(
            onClick = {
                button1Color = randomColor()
                button2Color = randomColor()
                button1Border = randomBorder()
                button2Border = randomBorder()
                button1BorderColor = randomColor()
                button2BorderColor = randomColor()
                checkWin()
            },
            modifier = Modifier.padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = button3Color),
            border = BorderStroke(button3Border, button3BorderColor)
        ) {
            Text(text = "Button 3", fontSize = 18.sp)
        }
    }
}


fun randomColor(): Color {
    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Cyan, Color.Magenta)
    return colors[Random.nextInt(colors.size)]
}

fun randomBorder(): Dp {
    val borders = listOf(1.dp, 2.dp, 4.dp, 8.dp)
    return borders[Random.nextInt(borders.size)]
}
