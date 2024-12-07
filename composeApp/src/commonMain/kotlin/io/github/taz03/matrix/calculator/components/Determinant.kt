package io.github.taz03.matrix.calculator.components

import androidx.compose.foundation.border
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set

@Composable
fun Determinant(matrix: D2Array<Int>) {
    val density = LocalDensity.current
    val strokeWidthPx = density.run { 2.dp.toPx() }

    val bracketColor = MaterialTheme.colorScheme.onSurface

    Column(
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
            }
    ) {
        val (row, column) = matrix.shape

        repeat(row) { i ->
            Row {
                repeat(column) { j ->
                    var input by remember { mutableStateOf(matrix[i, j].toString()) }
                    BasicTextField(
                        value = input,
                        onValueChange = {
                            input = it
                            matrix[i, j] = input.trim().toIntOrNull() ?: 0
                        },
                        modifier = Modifier.width(40.dp)
                            .padding(horizontal = 5.dp)
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.onSurface,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(5.dp),
                    )
                }
            }
        }

        Spacer(Modifier.height(4.dp))
    }
}
