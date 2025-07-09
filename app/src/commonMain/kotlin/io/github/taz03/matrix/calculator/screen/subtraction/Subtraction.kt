package io.github.taz03.matrix.calculator.screen.subtraction

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.taz03.matrix.calculator.components.BottomBar
import io.github.taz03.matrix.calculator.components.LabeledIncDecControls
import io.github.taz03.matrix.calculator.components.Matrix
import io.github.taz03.matrix.calculator.components.MatrixOnValueChange
import io.github.taz03.matrix.calculator.screen.subtraction.viewmodel.SubtractionViewModel

@Composable
fun Subtraction(
    viewModel: SubtractionViewModel = viewModel { SubtractionViewModel() }
) = Scaffold(
    bottomBar = {
        BottomBar(viewModel::calculate) {
            LabeledIncDecControls(
                label = "Rows",
                onDecrementClicked = viewModel::decrementRows,
                onIncrementClicked = viewModel::incrementRows,
                value = viewModel.rows
            )

            LabeledIncDecControls(
                label = "Columns",
                onDecrementClicked = viewModel::decrementColumns,
                onIncrementClicked = viewModel::incrementColumns,
                value = viewModel.columns
            )
        }
    }
) {
    Row(
        modifier = Modifier.padding(25.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Matrix(
            matrix = viewModel.matrixA,
            onValueChange = MatrixOnValueChange.onIntValueChange(viewModel.matrixA)
        )
        Spacer(Modifier.width(10.dp))
        Text("-")
        Spacer(Modifier.width(10.dp))
        Matrix(
            matrix = viewModel.matrixB,
            onValueChange = MatrixOnValueChange.onIntValueChange(viewModel.matrixB)
        )

        viewModel.difference?.let {
            Spacer(Modifier.width(10.dp))
            Text("=")
            Spacer(Modifier.width(10.dp))
            Matrix(it)
        }
    }
}
