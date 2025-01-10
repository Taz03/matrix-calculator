package io.github.taz03.matrix.calculator.screen.rank.viewmoel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.github.taz03.matrix.calculator.screen.determinant.viewmodel.DeterminantViewModel
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.zeros
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set
import kotlin.math.min

class RankViewModel : ViewModel() {
    var rows by mutableStateOf(1)
        private set

    var columns by mutableStateOf(1)
        private set

    var matrix by mutableStateOf(mk.zeros<Int>(1, 1))
        private set

    var rank by mutableStateOf<Int?>(null)

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
        var rank = min(rows, columns)

        while (rank > 0) {
            val subMatrix = mk.zeros<Int>(rank, rank)

            for (i in 0..rows - rank) {
                for (j in 0..columns - rank) {
                    for (k in 0 until rank) {
                        for (l in 0 until rank) {
                            subMatrix[k, l] = matrix[i + k, j + l]
                        }
                    }

                    if (DeterminantViewModel.calculateDeterminant(subMatrix) != 0.0) {
                        this.rank = rank
                        return
                    }
                }
            }

            rank--
        }

        this.rank = 0
    }

    private fun reshapeMatrices(oldRow: Int, oldColumn: Int) {
        val matrixNew = mk.zeros<Int>(rows, columns)

        for (i in 0 until oldRow) {
            for (j in 0 until oldColumn) {
                matrixNew[i, j] = matrix[i, j]
            }
        }

        matrix = matrixNew
    }
}