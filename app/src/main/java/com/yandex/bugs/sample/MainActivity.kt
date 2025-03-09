package com.yandex.bugs.sample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yandex.bugs.sample.ui.theme.BugsSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BugsSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StartScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun StartScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TaskButton("Задание 1 – Таймер", Task1Activity::class.java)
        TaskButton("Задание 2 – Canvas", Task2Activity::class.java)
        TaskButton("Задание 3 – TODO", Task3Activity::class.java)
        TaskButton("Задание 4 – TODO", Task4Activity::class.java)
        TaskButton("Задание 5 – DivKit", Task5Activity::class.java)
    }
}

@Composable
fun TaskButton(text: String, activityClass: Class<out Activity>) {
    val context = LocalContext.current
    Button(
        onClick = {
            activityClass.let { context.startActivity(Intent(context, activityClass)) }
        },
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Text(text = text, fontSize = 18.sp,modifier = Modifier.padding(6.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BugsSampleTheme {
        StartScreen()
    }
}