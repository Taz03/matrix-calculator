package io.github.taz03.matrix.calculator.screen.transpose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import io.github.taz03.matrix.calculator.components.Matrix
import io.github.taz03.matrix.calculator.screen.transpose.viewmodel.TransposeViewModel

@Composable
fun Transpose(
    viewModel: TransposeViewModel = viewModel { TransposeViewModel() }
) = Scaffold(
    bottomBar = {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                LabeledIncDecControls(
                    label = "Rows",
                    onDecrementClicked = viewModel::decrementRows,
                    onIncrementClicked = viewModel::incrementRows,
                    value = viewModel.rows
                )

                Spacer(Modifier.width(30.dp))

                LabeledIncDecControls(
                    label = "Columns",
                    onDecrementClicked = viewModel::decrementColumns,
                    onIncrementClicked = viewModel::incrementColumns,
                    value = viewModel.columns
                )
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
        Matrix(viewModel.matrix)

        viewModel.transpose?.let {
            Spacer(Modifier.width(10.dp))
            Text("=")
            Spacer(Modifier.width(10.dp))
            Matrix(it, false)
        }
    }
}