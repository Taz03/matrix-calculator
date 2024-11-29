package io.github.taz03.matrix.calculator.screen.subtraction.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.zeros
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set
import org.jetbrains.kotlinx.multik.ndarray.operations.minus

class SubtractionViewModel : ViewModel() {
    var rows by mutableStateOf(1)
        private set

    var columns by mutableStateOf(1)
        private set

    var matrixA by mutableStateOf(mk.zeros<Int>(1, 1))
        private set
    var matrixB by mutableStateOf(mk.zeros<Int>(1, 1))
        private set

    var difference by mutableStateOf<D2Array<Int>?>(null)
        private set

    fun decrementRows() {
        if (rows == 1) return

        rows--
        reshapeMatrices(rows, columns)
    }

    fun incrementRows() {
        rows++
        reshapeMatrices(rows - 1, columns)
    }

    fun decrementColumns() {
        if (columns == 1) return

        columns--
        reshapeMatrices(rows, columns)
    }

    fun incrementColumns() {
        columns++
        reshapeMatrices(rows, columns - 1)
    }

    fun calculate() {
        difference = matrixA - matrixB
    }

    private fun reshapeMatrices(oldRow: Int, oldColumn: Int) {
        val matrixANew = mk.zeros<Int>(rows, columns)
        val matrixBNew = mk.zeros<Int>(rows, columns)

        for (i in 0 until oldRow) {
            for (j in 0 until oldColumn) {
                matrixANew[i, j] = matrixA[i, j]
                matrixBNew[i, j] = matrixB[i, j]
            }
        }

        matrixA = matrixANew
        matrixB = matrixBNew
    }
}