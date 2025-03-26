package com.yandex.bugs.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yandex.bugs.sample.ui.theme.BugsSampleTheme

class Task4Activity : ComponentActivity() {

    companion object {
        var todoList = mutableStateListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BugsSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoScreen()
                }
            }
        }
    }

    @Composable
    fun TodoScreen() {
        var newTodoText = ""

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = newTodoText,
                    onValueChange = { newTodoText = it },
                    modifier = Modifier.weight(1f),
                    label = { Text("Новая задача") }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        todoList.add(newTodoText)
                    }
                ) {
                    Text("Добавить", color = Color.Red)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(todoList) { todo ->
                    TodoItem(
                        todo = todo,
                        onDelete = {
                            todoList.remove(it)
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun TodoItem(todo: String, onDelete: (String) -> Unit) {
        val todoText = remember { mutableStateOf(todo) }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = todoText.value)

                IconButton(onClick = { onDelete(todo) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Удалить"
                    )
                }
            }
        }
    }
}