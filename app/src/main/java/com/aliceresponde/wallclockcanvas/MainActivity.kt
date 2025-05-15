package com.aliceresponde.wallclockcanvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliceresponde.wallclockcanvas.ui.theme.Black
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                var milliseconds by remember { mutableLongStateOf(System.currentTimeMillis()) }
                val totalSeconds = milliseconds / 1_000f

                var seconds by remember {
                    mutableFloatStateOf(totalSeconds % 60f)
                }
                var minutes by remember {
                    mutableFloatStateOf((totalSeconds / 60f) % 60f)
                }
                var hours by remember {
                    mutableFloatStateOf((totalSeconds / 3_600f) % 12f)
                }

                LaunchedEffect(key1 = seconds) {
                    delay(1_000)
                    seconds += 1f         // 1 second is 1000 milliseconds
                    minutes += (1f / 60f)  // a minute is 60 seconds --added a fraction off min
                    hours += (1f / (3_600f))   // an hour is 60 minutes that is 3600 seconds
                }

                Clock(
                    hours = hours,
                    minutes = minutes,
                    seconds = seconds
                )

                Text(
                    text = "${hours} h \n${minutes} mins \n ${seconds} sec",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 62.dp),
                    style = TextStyle(fontSize = 22.sp),
                    color = Black
                )
            }
        }
    }
}

