package com.yandex.bugs.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yandex.bugs.sample.ui.theme.BugsSampleTheme

class Task3Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BugsSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TextEditorApp()
                }
            }
        }
    }

    @Composable
    fun TextEditorApp() {
        var text by remember { mutableStateOf("") }
        var fontSize by remember { mutableFloatStateOf(2f) }
        var colorIndex by remember { mutableFloatStateOf(2f) }
        var rotation by remember { mutableIntStateOf(0) }
        var duplication by remember { mutableFloatStateOf(1f) }
        var originalText by remember { mutableStateOf("") }

        val colors = listOf(
            Color.Black, Color.Red, Color.Green, Color.Blue,
            Color.Yellow, Color.Cyan, Color.Magenta, Color.DarkGray,
            Color.LightGray
        )

        val textColor = colors[colorIndex.toInt() - 1]

        val textSize = when {
            fontSize <= 1f -> 12.sp
            fontSize <= 2f -> 14.sp
            fontSize <= 3f -> 16.sp
            fontSize <= 4f -> 18.sp
            else -> 220.sp
        }

        val infiniteTransition = rememberInfiniteTransition()
        val rotationAngle by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 500),
                repeatMode = RepeatMode.Restart
            )
        )

        val actualRotation = if (rotation > 0) {
            rotationAngle
        } else {
            0f
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                    originalText = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .rotate(actualRotation),
                textStyle = TextStyle(fontSize = textSize, color = textColor),
                label = { Text("Введите текст") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Размер текста: ${fontSize.toInt()}")
            Slider(
                value = fontSize,
                onValueChange = {
                    fontSize = it
                },
                valueRange = 1f..5f,
                steps = 3,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Text("Цвет текста: ${colorIndex.toInt()}")
            Slider(
                value = colorIndex,
                onValueChange = {
                    fontSize = it
                },
                valueRange = 1f..10f,
                steps = 8,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Text("Вращение текста")
            Slider(
                value = rotation.toFloat(),
                onValueChange = { rotation = it.toInt() },
                valueRange = 1f..10f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Text("Дублирование текста: ${duplication.toInt()} раз")
            Slider(
                value = duplication,
                onValueChange = {
                    duplication = it
                    val repeats = duplication.toInt()
                    text = originalText.repeat(repeats).repeat(repeats)
                },
                valueRange = 1f..4f,
                steps = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Button(
                onClick = {
                    text = ""
                    originalText = ""
                    fontSize = 2f
                    colorIndex = 2f
                    duplication = 1f
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Сбросить все")
            }
        }
    }
}
