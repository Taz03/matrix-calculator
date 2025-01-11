package io.github.taz03.matrix.calculator.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set

object MatrixOnValueChange {
    fun onIntValueChange(matrix: D2Array<Int>): ((Int, Int, String) -> Unit) = { i, j, v ->
        matrix[j, i] = v.trim().toIntOrNull() ?: 0
    }

    fun onDoubleValueChange(matrix: D2Array<Double>): ((Int, Int, String) -> Unit) = { i, j, v ->
        matrix[j, i] = v.trim().toDoubleOrNull() ?: 0.0
    }
}

@Composable
fun Matrix(
    matrix: D2Array<*>,
    onValueChange: ((Int, Int, String) -> Unit)? = null,
    determinant: Boolean = false
) {
    val density = LocalDensity.current
    val strokeWidthPx = density.run { 2.dp.toPx() }

    val bracketColor = MaterialTheme.colorScheme.onSurface

    val (row, column) = matrix.shape
    Row(
        modifier = Modifier
            .padding(8.dp)
            .drawBehind {
                val width = size.width
                val height = size.height - strokeWidthPx / 2

                drawLine(
                    color = bracketColor,
                    start = Offset(0f, 0f),
                    end = Offset(0f, height),
                    strokeWidth = strokeWidthPx
                )
                drawLine(
                    color = bracketColor,
                    start = Offset(width, 0f),
                    end = Offset(width, height),
                    strokeWidth = strokeWidthPx
                )

                if (!determinant) {
                    val modifier = when (column) {
                        1 -> 0.1f
                        in 2..3 -> 0.06f
                        in 4..10 -> 0.02f
                        in 11..20 -> 0.015f
                        else -> 0.01f
                    }

                    drawLine(
                        color = bracketColor,
                        start = Offset(0f, 0f),
                        end = Offset(width * modifier, 0f),
                        strokeWidth = strokeWidthPx
                    )
                    drawLine(
                        color = bracketColor,
                        start = Offset(width - (width * modifier), 0f),
                        end = Offset(width, 0f),
                        strokeWidth = strokeWidthPx
                    )

                    drawLine(
                        color = bracketColor,
                        start = Offset(0f, height),
                        end = Offset(width * modifier, height),
                        strokeWidth = strokeWidthPx
                    )
                    drawLine(
                        color = bracketColor,
                        start = Offset(width - (width * modifier), height),
                        end = Offset(width, height),
                        strokeWidth = strokeWidthPx
                    )
                }
            }
    ) {
        repeat(column) { i ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                repeat(row) { j ->
                    if (onValueChange != null) {
                        var input by remember { mutableStateOf(matrix[j, i].toString()) }
                        BasicTextField(
                            value = input,
                            onValueChange = {
                                input = it
                                onValueChange(i, j, input)
                            },
                            modifier = Modifier.width(40.dp)
                                .padding(horizontal = 5.dp)
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.outline,
                                    shape = MaterialTheme.shapes.small
                                )
                                .padding(5.dp),
                            textStyle = TextStyle.Default.copy(
                                color = MaterialTheme.colorScheme.onSurface
                            ),
                            singleLine = true,
                            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface)
                        )
                    } else Text(
                        text = matrix[j, i].toString(),
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )
                }
            }
        }

        Spacer(Modifier.height(4.dp))
    }
}
