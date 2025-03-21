package com.yandex.bugs.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.yandex.bugs.sample.ui.theme.BugsSampleTheme
import com.yandex.div.DivDataTag
import com.yandex.div.compose.interop.DivView
import com.yandex.div.compose.interop.scoped
import com.yandex.div.core.Div2Context
import com.yandex.div.core.DivConfiguration
import com.yandex.div.data.DivParsingEnvironment
import com.yandex.div.json.ParsingErrorLogger
import com.yandex.div.markdown.DivMarkdownExtensionHandler
import com.yandex.div.picasso.PicassoDivImageLoader
import com.yandex.div2.DivData
import org.json.JSONObject

class Task5Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val divString = assets.open("pong.json").bufferedReader().use { it.readText() }
        val divJson = JSONObject(divString)

        val templatesJson = divJson.optJSONObject("templates")
        val cardJson = divJson.getJSONObject("card")

        val environment = DivParsingEnvironment(ParsingErrorLogger.ASSERT).apply {
            if (templatesJson != null) parseTemplates(templatesJson)
        }

        val divData = DivData(environment, cardJson)

        setContent {
            BugsSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Div2Context(
                        baseContext = this,
                        configuration = createDivConfiguration(),
                        lifecycleOwner = this,
                    ).scoped {
                        DivView(
                            data = divData,
                            tag = DivDataTag("not_so_simple_screen"),
                        )
                    }
                }
            }
        }
    }

    private fun createDivConfiguration(): DivConfiguration {
        return DivConfiguration.Builder(PicassoDivImageLoader(this))
            .extension(DivMarkdownExtensionHandler(this))
            .visualErrorsEnabled(false)
            .build()
    }
}
