package io.github.taz03.matrix.calculator.screen.dotproduct

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
        val windowSize = calculateWindowSizeClass()
        Row(
            modifier = Modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Matrix A")
                    Spacer(Modifier.width(15.dp))

                    LabeledIncDecControls(
                        label = "Rows",
                        onDecrementClicked = viewModel::decrementRowsA,
                        onIncrementClicked = viewModel::incrementRowsA,
                        value = viewModel.rowsA
                    )

                    Spacer(Modifier.width(30.dp))

                    LabeledIncDecControls(
                        label = "Columns",
                        onDecrementClicked = viewModel::decrementColumnsA,
                        onIncrementClicked = viewModel::incrementColumnsA,
                        value = viewModel.columnsA
                    )
                }

                Spacer(Modifier.height(25.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Matrix B")
                    Spacer(Modifier.width(15.dp))

                    LabeledIncDecControls(
                        label = "Rows",
                        onDecrementClicked = viewModel::decrementRowsB,
                        onIncrementClicked = viewModel::incrementRowsB,
                        value = viewModel.rowsB
                    )

                    Spacer(Modifier.width(30.dp))

                    LabeledIncDecControls(
                        label = "Columns",
                        onDecrementClicked = viewModel::decrementColumnsB,
                        onIncrementClicked = viewModel::incrementColumnsB,
                        value = viewModel.columnsB
                    )
                }

                if (windowSize.widthSizeClass == WindowWidthSizeClass.Compact) Button(
                    onClick = viewModel::calculate,
                    modifier = Modifier.align(Alignment.End)
                        .padding(top = 20.dp)
                ) {
                    Text("Calculate")
                }
            }

            if (windowSize.widthSizeClass != WindowWidthSizeClass.Compact) Button(onClick = viewModel::calculate) {
                Text("Calculate")
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
