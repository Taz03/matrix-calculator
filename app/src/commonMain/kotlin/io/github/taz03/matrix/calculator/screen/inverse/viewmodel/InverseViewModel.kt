package io.github.taz03.matrix.calculator.screen.inverse.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.github.taz03.matrix.calculator.screen.determinant.viewmodel.DeterminantViewModel
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.zeros
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set
import org.jetbrains.kotlinx.multik.ndarray.operations.div

class InverseViewModel : ViewModel() {
    var side by mutableStateOf(1)
        private set

    var matrix by mutableStateOf(mk.zeros<Int>(1, 1))
        private set

    var inverse by mutableStateOf<D2Array<Double>?>(null)
        private set

    fun decrementSide() {
        if (side == 1) return

        side--
        reshapeMatrix(side)
    }

    fun incrementSide() {
        side++
        reshapeMatrix(side - 1)
    }

    fun calculate() {
        val adjointMatrix = mk.zeros<Double>(side, side)

        for (i in 0 until side) {
            for (j in 0 until side) {
                val coefficient = if (i + j % 2 == 0) 1 else -1

                val subMatrix = mk.zeros<Int>(side - 1, side - 1)
                for (k in 0 until side) {
                    if (k == i) continue

                    for (l in 0 until side) {
                        if (l == j) continue

                        subMatrix[if (k > i) k - 1 else k, if (l > j) l - 1 else l] = matrix[k, l]
                    }
                }

                adjointMatrix[j, i] = coefficient.toDouble() * DeterminantViewModel.calculateDeterminant(subMatrix)
            }
        }

        inverse = adjointMatrix.div(DeterminantViewModel.calculateDeterminant(matrix).toDouble())
    }

    private fun reshapeMatrix(oldSide: Int) {
        val matrixANew = mk.zeros<Int>(side, side)

        for (i in 0 until oldSide)
            for (j in 0 until oldSide)
                matrixANew[i, j] = matrix[i, j]

        matrix = matrixANew
    }
}