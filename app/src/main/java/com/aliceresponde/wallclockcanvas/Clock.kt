package com.aliceresponde.wallclockcanvas

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aliceresponde.wallclockcanvas.ui.theme.Black
import com.aliceresponde.wallclockcanvas.ui.theme.Gray
import com.aliceresponde.wallclockcanvas.ui.theme.Green
import com.aliceresponde.wallclockcanvas.ui.theme.Purple40
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Clock(
    clockStyle: ClockStyle = ClockStyle(),
    seconds: Float,
    minutes: Float,
    hours: Float,
    radius: Dp = 100.dp,
) {
    Canvas(
        modifier = Modifier
            .size(radius * 2f)
            .padding(16.dp),
    ) {
        drawContext.canvas.nativeCanvas.apply {
            drawCircle(
                center.x,
                center.y,
                radius.toPx(),
                Paint().apply {
                    color = Color.WHITE
                    setShadowLayer(
                        50f,
                        0f,
                        0f,
                        Color.argb(50, 0, 0, 0)
                    )
                }
            )
        }

        val anglePerMinuteDegree = 360f / 60f //degree
        for (i in 0..59) {
            val angle = anglePerMinuteDegree * i
            val isHour = i % 5 == 0
            val lineColor = if (isHour) Black else Gray
            val angleInRadians = angle * Math.PI / 180f
            val minuteLineLength = if (isHour) radius * 0.25f else radius * 0.15f

            val startLine = Offset(
                x = center.x + (radius.toPx() - minuteLineLength.toPx()) * cos(angleInRadians).toFloat(),
                y = center.y + (radius.toPx() - minuteLineLength.toPx()) * sin(angleInRadians).toFloat()
            )

            val endLine = Offset(
                x = center.x + radius.toPx() * cos(angleInRadians).toFloat(),
                y = center.y + radius.toPx() * sin(angleInRadians).toFloat()
            )

            // draw minute/hour lines -- external
            drawLine(
                color = lineColor,
                start = startLine,
                end = endLine
            )
        }

        // hour, minute, second
        val hourEndLine = Offset(
            x = center.x ,
            y = center.y + 50.dp.toPx()
        )

        val minuteEndLine = Offset(
            x = center.x ,
            y = center.y + 70.dp.toPx()
        )

        val secondEndLine = Offset(
            x = center.x ,
            y = center.y + 70.dp.toPx()
        )

        val hourAngle = hours * (360f / 12f)
        rotate(
            degrees = hourAngle -180
        ) {
            drawLine(
                color = clockStyle.hoursColor,
                start = center,
                end = (hourEndLine),
                strokeWidth = clockStyle.hoursWidth,
                cap = StrokeCap.Round
            )
        }

        val minAngle = minutes * (360f / 60f)
        rotate(
            degrees = minAngle -180
        ) {

            drawLine(
                color = clockStyle.minutesColor,
                start = center,
                end = minuteEndLine,
                strokeWidth = clockStyle.minutesWidth,
                cap = StrokeCap.Round
            )
        }

        val secondsAngle = seconds * (360f / 60f)
        rotate(
            degrees = secondsAngle -180
        ) {
            drawLine(
                color = clockStyle.secondsColor,
                start = center,
                end = secondEndLine,
                strokeWidth = clockStyle.secondsWidth,
                cap = StrokeCap.Round
            )
        }

    }
}