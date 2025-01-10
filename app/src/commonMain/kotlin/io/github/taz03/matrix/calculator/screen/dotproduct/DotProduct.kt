package io.github.taz03.matrix.calculator.screen.dotproduct

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.taz03.matrix.calculator.components.LabeledIncDecControls
import io.github.taz03.matrix.calculator.components.IntMatrix
import io.github.taz03.matrix.calculator.screen.dotproduct.viewmodel.DotProductViewModel

@Composable
fun DotProduct(
    viewModel: DotProductViewModel = viewModel { DotProductViewModel() }
) = Scaffold(
    bottomBar = {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
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
            }

            Spacer(Modifier.weight(1f))

            Button(onClick = viewModel::calculate) {
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
        IntMatrix(viewModel.matrixA)
        IntMatrix(viewModel.matrixB)

        viewModel.product?.let {
            Spacer(Modifier.width(10.dp))
            Text("=")
            Spacer(Modifier.width(10.dp))
            IntMatrix(it, false)
        }
    }
}
