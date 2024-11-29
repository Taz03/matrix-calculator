package io.github.taz03.matrix.calculator

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "MatrixCalculator",
    ) {
        App()
    }
}