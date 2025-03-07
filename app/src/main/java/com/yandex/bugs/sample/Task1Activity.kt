package com.yandex.bugs.sample

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yandex.bugs.sample.ui.theme.BugsSampleTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.ArrayList

class Task1Activity : ComponentActivity() {
    // Ошибка 1: Сохранение контекста в статическом поле вызывает утечку памяти
    companion object {
        private lateinit var appContext: Context
    }

    private var counter = mutableStateOf(0) // Ошибка 2: Не используется remember
    private val numbers = ArrayList<Int>() // Будет храниться список чисел
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContext = this // Инициализация утечки памяти

        // Ошибка 3: Не освобождаются ресурсы
        handler = Handler()

        // Заполнение списка
        for (i in 1..100) {
            numbers.add(i)
        }

        setContent {
            BugsSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CounterScreen()
                }
            }
        }
    }

    @Composable
    fun CounterScreen() {
        val context = LocalContext.current
        // Ошибка 4: Не использовать remember здесь приведет к потере state при рекомпозиции
        var counter by remember { mutableStateOf(0) }
        var inputText by remember { mutableStateOf("") }

        // Ошибка 5: Это вызовется при каждой рекомпозиции, создав бесконечный цикл
        LaunchedEffect(Unit) {
            counter += 1
        }

        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Счетчик: $counter",
                fontSize = 24.sp,
                // Ошибка 6: Модификатор padding внутри Text не работает так, как ожидается
                modifier = Modifier.padding(100.dp)
            )

            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = { Text("Введите число") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    // Ошибка 7: Нет проверки на корректность ввода перед преобразованием
                    val input = inputText.toInt()
                    counter = input

                    // Ошибка 8: Обращение к UI из фонового потока
                    Thread {
                        Thread.sleep(1000) // Имитация тяжелой работы
                        // Это приведет к краху, так как обновляем UI не в главном потоке
                        counter += 1
                    }.start()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Установить значение")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        // Ошибка 9: Бесконечный цикл может привести к ANR
                        for (i in 1..Int.MAX_VALUE) {
                            delay(100)
                            counter += 1
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Начать увеличение")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Ошибка 10: Странные размеры, плохая иерархия
            Box(
                modifier = Modifier
                    .fillMaxWidth(-0.5f) // Отрицательное значение
                    .height(200.dp)
                    .background(Color.Red)
            ) {
                // Список чисел, но с ошибкой в данных
                LazyColumn {
                    // Ошибка 11: Неправильная фильтрация, будет пустой список
                    items(numbers.filter { it > 1000 }) { number ->
                        Text(
                            text = "Число: $number",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    // Ошибка 12: Потенциальный NullPointerException
                                    val nullableString: String? = null
                                    Toast.makeText(
                                        context,
                                        nullableString!!.length.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        )
                    }
                }
            }
        }
    }

    // Ошибка 13: Незакрытые ресурсы
    override fun onDestroy() {
        super.onDestroy()
        // Отсутствует очистка handler
    }
}
