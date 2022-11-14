package com.weather.ui.screen.weather

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class CurvedShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline = Outline.Generic(
        path = Path().apply {
            reset()
            lineTo(0f, size.height / 2)
            arcTo(
                rect = Rect(
                    Offset(-size.width + 50, size.height / 2),
                    Size(size.width * 2, size.height)
                ),
                startAngleDegrees = 270f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )
            lineTo(size.width, 0f)
            lineTo(0f, 0f)
            close()
        }
    )
}