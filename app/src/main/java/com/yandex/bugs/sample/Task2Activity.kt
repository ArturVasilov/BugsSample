package com.yandex.bugs.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yandex.bugs.sample.ui.theme.BugsSampleTheme
import kotlinx.coroutines.delay
import kotlin.math.sin

class Task2Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BugsSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GeometricLandscapeComposition()
                }
            }
        }
    }

    @Composable
    fun GeometricLandscapeComposition() {
        var timeMillis by remember { mutableLongStateOf(0L) }

        LaunchedEffect(Unit) {
            while(true) {
                timeMillis = System.currentTimeMillis()
                delay(16)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x001A237E))
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val width = size.width
                val height = size.height

                repeat(80) {
                    drawCircle(
                        color = Color.White.copy(alpha = 0.7f + (Math.random() * 0.3).toFloat()),
                        radius = (1 + Math.random() * 3).toFloat().dp.toPx(),
                        center = Offset(
                            x = (Math.random() * width).toFloat(),
                            y = (Math.random() * height * 0.6f).toFloat()
                        )
                    )
                }

                drawCircle(
                    color = Color(0xFFFFA000),
                    radius = width * 0.12f,
                    center = Offset(width * 0.8f, height * 0.2f)
                )

                for (i in 0 until 12) {
                    rotate(degrees = i * 35f, pivot = Offset(width * 0.8f, height * 0.2f)) {
                        drawRect(
                            color = Color(0xFFFFB74D),
                            topLeft = Offset(width * 0.8f, height * 0.08f),
                            size = Size(width * 0.01f, height * 0.05f)
                        )
                    }
                }

                val mountainPath1 = Path().apply {
                    moveTo(0f, height * 0.7f)
                    lineTo(width * 0.35f, height * 0.4f)
                    lineTo(width * 0.7f, height * 0.7f)
                    close()
                }
                drawPath(
                    path = mountainPath1,
                    color = Color(0xFF5D4037)
                )

                val mountainPath2 = Path().apply {
                    moveTo(width * 0.3f, height * 0.7f)
                    lineTo(width * 0.65f, height * 0.35f)
                    lineTo(width, height * 0.7f)
                    close()
                }
                drawPath(
                    path = mountainPath2,
                    color = Color(0xFF8D6E63)
                )

                val snowPath1 = Path().apply {
                    moveTo(width * 0.55f, height * 0.4f)
                    lineTo(width * 0.3f, height * 0.45f)
                    lineTo(width * 0.4f, height * 0.45f)
                    close()
                }
                drawPath(
                    path = snowPath1,
                    color = Color.White
                )

                val snowPath2 = Path().apply {
                    moveTo(width * 0.65f, height * 0.35f)
                    lineTo(width * 0.6f, height * 0.4f)
                    lineTo(width * 0.7f, height * 0.4f)
                    close()
                }
                drawPath(
                    path = snowPath2,
                    color = Color.White
                )

                drawRect(
                    color = Color(0xFF2E7D32),
                    topLeft = Offset(0f, height * 0.7f),
                    size = Size(width, height * 0.3f)
                )

                drawRect(
                    color = Color(0xFF4E342E),
                    topLeft = Offset(width * 0.15f, height * 0.6f),
                    size = Size(width * 0.02f, height * 0.1f)
                )

                val treeTop1 = Path().apply {
                    moveTo(width * 0.08f, height * 0.6f)
                    lineTo(width * 0.16f, height * 0.45f)
                    lineTo(width * 0.24f, height * 0.6f)
                    close()
                }
                drawPath(
                    path = treeTop1,
                    color = Color(0xFF388E3C)
                )

                drawRect(
                    color = Color(0xFF4E342E),
                    topLeft = Offset(width * 0.85f, height * 0.6f),
                    size = Size(width * 0.02f, height * 0.1f)
                )

                val treeTop2 = Path().apply {
                    moveTo(width * 0.78f, height * 0.6f)
                    lineTo(width * 0.86f, height * 0.42f)
                    lineTo(width * 0.94f, height * 0.6f)
                    close()
                }
                drawPath(
                    path = treeTop2,
                    color = Color(0xFF2E7D32)
                )

                drawOval(
                    color = Color(0xFF2E7D32),
                    topLeft = Offset(width * 0.25f, height * 0.75f),
                    size = Size(width * 0.5f, height * 0.15f)
                )

                drawOval(
                    color = Color(0xFFFFB74D).copy(alpha = 0.3f),
                    topLeft = Offset(width * 0.45f, height * 0.77f),
                    size = Size(width * 0.1f, height * 0.05f)
                )

                translate(left = width * 0.2f, top = height * 0.15f) {
                    drawCircle(
                        color = Color.White.copy(alpha = 0.8f),
                        radius = width * 0.05f,
                        center = Offset(0f, 0f)
                    )
                    drawCircle(
                        color = Color.White.copy(alpha = 0.8f),
                        radius = width * 0.06f,
                        center = Offset(width * 0.06f, 0f)
                    )
                    drawCircle(
                        color = Color.White.copy(alpha = 0.8f),
                        radius = width * 0.04f,
                        center = Offset(width * 0.1f, 0f)
                    )
                }

                translate(left = width * 0.5f, top = height * 0.1f) {
                    drawCircle(
                        color = Color.White.copy(alpha = 0.7f),
                        radius = width * 0.04f,
                        center = Offset(0f, 0f)
                    )
                    drawCircle(
                        color = Color.White.copy(alpha = 0.7f),
                        radius = width * 0.05f,
                        center = Offset(width * 0.05f, 0f)
                    )
                    drawCircle(
                        color = Color.White.copy(alpha = 0.7f),
                        radius = width * 0.03f,
                        center = Offset(width * 0.09f, 0f)
                    )
                }

                val boatPath = Path().apply {
                    moveTo(width * 0.4f + sin(timeMillis / 1000.0).toFloat() * width * 0.25f, height * 0.8f)
                    lineTo(width * 0.45f + sin(timeMillis / 1000.0).toFloat() * width * 0.25f, height * 0.82f)
                    lineTo(width * 0.5f + sin(timeMillis / 1000.0).toFloat() * width * 0.25f, height * 0.8f)
                    close()
                }
                drawPath(
                    path = boatPath,
                    color = Color(0xFF795548)
                )

                val sailPath = Path().apply {
                    moveTo(width * 0.45f + sin(timeMillis / 950.0).toFloat() * width * 0.25f, height * 0.8f)
                    lineTo(width * 0.45f + sin(timeMillis / 950.0).toFloat() * width * 0.25f, height * 0.75f)
                    lineTo(width * 0.48f + sin(timeMillis / 950.0).toFloat() * width * 0.25f, height * 0.78f)
                    close()
                }
                drawPath(
                    path = sailPath,
                    color = Color.White
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GeometricLandscapePreview() {
        GeometricLandscapeComposition()
    }
}
