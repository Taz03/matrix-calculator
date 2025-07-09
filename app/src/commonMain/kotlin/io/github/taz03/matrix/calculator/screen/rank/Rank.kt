package io.github.taz03.matrix.calculator.screen.rank

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import io.github.taz03.matrix.calculator.screen.rank.viewmoel.RankViewModel

@Composable
fun Rank(viewModel: RankViewModel = viewModel { RankViewModel() }) = Scaffold(
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
    Column(
        modifier = Modifier.padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("A = ")

            Matrix(
                matrix = viewModel.matrix,
                onValueChange = MatrixOnValueChange.onIntValueChange(viewModel.matrix)
            )
        }

        viewModel.rank?.let { Text("ρ(A) = $it") }
    }
}
