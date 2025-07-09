package io.github.taz03.matrix.calculator.screen.dotproduct

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.taz03.matrix.calculator.components.BottomBar
import io.github.taz03.matrix.calculator.components.LabeledIncDecControls
import io.github.taz03.matrix.calculator.components.Matrix
import io.github.taz03.matrix.calculator.components.MatrixOnValueChange
import io.github.taz03.matrix.calculator.screen.dotproduct.viewmodel.DotProductViewModel

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun DotProduct(
    viewModel: DotProductViewModel = viewModel { DotProductViewModel() }
) = Scaffold(
    bottomBar = {
        BottomBar(viewModel::calculate) {
            Column(verticalArrangement = Arrangement.spacedBy(30.dp)) {
                MatrixControls(
                    matrix = "A",
                    rows = viewModel.rowsA,
                    onDecrementRowsClicked = viewModel::decrementRowsA,
                    onIncrementRowsClicked = viewModel::incrementRowsA,
                    columns = viewModel.columnsA,
                    onDecrementColumnsClicked = viewModel::decrementColumnsA,
                    onIncrementColumnsClicked = viewModel::incrementColumnsA
                )

                MatrixControls(
                    matrix = "B",
                    rows = viewModel.rowsB,
                    onDecrementRowsClicked = viewModel::decrementRowsB,
                    onIncrementRowsClicked = viewModel::incrementRowsB,
                    columns = viewModel.columnsB,
                    onDecrementColumnsClicked = viewModel::decrementColumnsB,
                    onIncrementColumnsClicked = viewModel::incrementColumnsB
                )
            }
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
        Text("•")
        Matrix(
            matrix = viewModel.matrixB,
            onValueChange = MatrixOnValueChange.onIntValueChange(viewModel.matrixB)
        )

        viewModel.product?.let {
            Spacer(Modifier.width(10.dp))
            Text("=")
            Spacer(Modifier.width(10.dp))
            Matrix(it)
        }
    }
}

@Composable
private fun MatrixControls(
    matrix: String,
    rows: Int,
    onDecrementRowsClicked: () -> Unit,
    onIncrementRowsClicked: () -> Unit,
    columns: Int,
    onDecrementColumnsClicked: () -> Unit,
    onIncrementColumnsClicked: () -> Unit,
) = Row(verticalAlignment = Alignment.CenterVertically) {
    Text("Matrix $matrix")
    Spacer(Modifier.width(15.dp))

    LabeledIncDecControls(
        label = "Rows",
        value = rows,
        onDecrementClicked = onDecrementRowsClicked,
        onIncrementClicked = onIncrementRowsClicked
    )

    Spacer(Modifier.width(30.dp))

    LabeledIncDecControls(
        label = "Columns",
        value = columns,
        onDecrementClicked = onDecrementColumnsClicked,
        onIncrementClicked = onIncrementColumnsClicked
    )
}
