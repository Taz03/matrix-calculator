package io.github.taz03.matrix.calculator

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document

external fun removeSpinner()

@OptIn(ExperimentalComposeUiApi::class)
fun main() = ComposeViewport(document.body!!) {
    removeSpinner()
    App()
}