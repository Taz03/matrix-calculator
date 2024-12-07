package io.github.taz03.matrix.calculator.screen.transpose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.zeros
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set

class TransposeViewModel : ViewModel() {
    var rows by mutableStateOf(1)
        private set

    var columns by mutableStateOf(1)
        private set

    var matrix by mutableStateOf(mk.zeros<Int>(1, 1))
        private set

    var transpose by mutableStateOf<D2Array<Int>?>(null)
        private set

    fun decrementRows() {
        if (rows == 1) return

        rows--
        reshapeMatrix(rows, columns)
    }

    fun incrementRows() {
        rows++
        reshapeMatrix(rows - 1, columns)
    }

    fun decrementColumns() {
        if (columns == 1) return

        columns--
        reshapeMatrix(rows, columns)
    }

    fun incrementColumns() {
        columns++
        reshapeMatrix(rows, columns - 1)
    }

    fun calculate() {
        transpose = matrix.transpose()
    }

    private fun reshapeMatrix(oldRow: Int, oldColumn: Int) {
        val matrixNew = mk.zeros<Int>(rows, columns)

        for (i in 0 until oldRow) {
            for (j in 0 until oldColumn) {
                matrixNew[i, j] = matrix[i, j]
            }
        }

        matrix = matrixNew
    }
}