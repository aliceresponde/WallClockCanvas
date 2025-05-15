package com.aliceresponde.wallclockcanvas

import androidx.compose.ui.graphics.Color
import com.aliceresponde.wallclockcanvas.ui.theme.Black
import com.aliceresponde.wallclockcanvas.ui.theme.Green
import com.aliceresponde.wallclockcanvas.ui.theme.Purple40

data
class ClockStyle(
    val secondsColor: Color = Green,
    val minutesColor: Color = Black,
    val hoursColor: Color = Purple40,
    val secondsWidth: Float = 2f,
    val minutesWidth: Float = 3f,
    val hoursWidth: Float = 15f,
)
