package io.github.taz03.matrix.calculator.screen.multiplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.jetbrains.kotlinx.multik.api.linalg.dot
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.zeros
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set

class MultiplicationViewModel : ViewModel() {
    var matrixA by mutableStateOf(mk.zeros<Int>(1, 1))
        private set
    var rowsA by mutableStateOf(1)
        private set
    var columnsA by mutableStateOf(1)
        private set

    var matrixB by mutableStateOf(mk.zeros<Int>(1, 1))
        private set
    var rowsB by mutableStateOf(1)
        private set
    var columnsB by mutableStateOf(1)
        private set

    var product by mutableStateOf<D2Array<Int>?>(null)
        private set

    fun decrementRowsA() {
        if (rowsA == 1) return

        rowsA--
        reshapeMatrices(rowsA, columnsA, rowsB, columnsB)
    }

    fun incrementRowsA() {
        rowsA++
        reshapeMatrices(rowsA - 1, columnsA, rowsB, columnsB)
    }

    fun decrementColumnsA() {
        if (columnsA == 1) return

        columnsA--
        rowsB = columnsA
        reshapeMatrices(rowsA, columnsA, rowsB, columnsB)
    }

    fun incrementColumnsA() {
        columnsA++
        rowsB = columnsA
        reshapeMatrices(rowsA, columnsA - 1, columnsA - 1, columnsB)
    }

    fun decrementRowsB() {
        if (rowsB == 1) return

        rowsB--
        columnsA = rowsB
        reshapeMatrices(rowsA, columnsA, rowsB, columnsB)
    }

    fun incrementRowsB() {
        rowsB++
        columnsA = rowsB
        reshapeMatrices(rowsA, rowsB - 1, rowsB - 1, columnsB)
    }

    fun decrementColumnsB() {
        if (columnsB == 1) return

        columnsB--
        reshapeMatrices(rowsA, columnsA, rowsB, columnsB)
    }

    fun incrementColumnsB() {
        columnsB++
        reshapeMatrices(rowsA, columnsA, rowsB, columnsB - 1)
    }

    fun calculate() {
        product = matrixA dot matrixB
    }

    private fun reshapeMatrices(oldRowA: Int, oldColumnA: Int, oldRowB: Int, oldColumnB: Int) {
        val matrixANew = mk.zeros<Int>(rowsA, columnsA)
        val matrixBNew = mk.zeros<Int>(rowsB, columnsB)

        for (i in 0 until oldRowA) {
            for (j in 0 until oldColumnA) {
                matrixANew[i, j] = matrixA[i, j]
            }
        }

        for (i in 0 until oldRowB) {
            for (j in 0 until oldColumnB) {
                matrixBNew[i, j] = matrixB[i, j]
            }
        }

        matrixA = matrixANew
        matrixB = matrixBNew
    }
}