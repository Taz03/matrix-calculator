package io.github.taz03.matrix.calculator.screen.determinant

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
import io.github.taz03.matrix.calculator.screen.determinant.viewmodel.DeterminantViewModel

@Composable
fun Determinant(
    viewModel: DeterminantViewModel = viewModel { DeterminantViewModel() }
) = Scaffold(
    bottomBar = {
        BottomBar(viewModel::calculate) {
            LabeledIncDecControls(
                label = "Side",
                onDecrementClicked = viewModel::decrementSide,
                onIncrementClicked = viewModel::incrementSide,
                value = viewModel.side
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
            matrix = viewModel.matrix,
            onValueChange = MatrixOnValueChange.onIntValueChange(viewModel.matrix),
            determinant = true
        )

        viewModel.determinant?.let {
            Spacer(Modifier.width(10.dp))
            Text("=")
            Spacer(Modifier.width(10.dp))
            Text(it.toString())
        }
    }
}
