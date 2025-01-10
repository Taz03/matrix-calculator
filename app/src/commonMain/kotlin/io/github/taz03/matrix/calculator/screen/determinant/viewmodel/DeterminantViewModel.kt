package io.github.taz03.matrix.calculator.screen.determinant.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.zeros
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set

class DeterminantViewModel : ViewModel() {
    companion object {
        fun calculateDeterminant(m: D2Array<Int>): Int {
            val n = m.shape[0]

            if (n == 1) return m[0, 0]
            if (n == 2) return (m[0, 0] * m[1, 1]) - (m[0, 1] * m[1, 0])

            var d = 0.0
            for (col in 0 until n) {
                val subMatrix = mk.zeros<Int>(n - 1, n - 1)
                for (i in 1 until n) {
                    for (j in 0 until n) {
                        if (j < col) subMatrix[i - 1, j] = m[i, j]
                        else if (j > col) subMatrix[i - 1, j - 1] = m[i, j]
                    }
                }

                val sign = if (col % 2 == 0) 1 else - 1
                d += sign * m[0, col] * calculateDeterminant(subMatrix)
            }

            return d.toInt()
        }
    }

    var side by mutableStateOf(1)
        private set

    var matrix by mutableStateOf(mk.zeros<Int>(1, 1))
        private set

    var determinant by mutableStateOf<Int?>(null)
        private set

    fun decrementSide() {
        if (side == 1) return

        side--
        reshapeMatrices(side)
    }

    fun incrementSide() {
        side++
        reshapeMatrices(side - 1)
    }

    fun calculate() {
        determinant = calculateDeterminant(matrix)
    }

    private fun reshapeMatrices(oldSide: Int) {
        val matrixANew = mk.zeros<Int>(side, side)

        for (i in 0 until oldSide)
            for (j in 0 until oldSide)
                matrixANew[i, j] = matrix[i, j]

        matrix = matrixANew
    }
}
