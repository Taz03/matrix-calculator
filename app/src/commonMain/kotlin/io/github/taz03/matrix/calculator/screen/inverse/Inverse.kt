package io.github.taz03.matrix.calculator.screen.inverse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.taz03.matrix.calculator.components.LabeledIncDecControls
import io.github.taz03.matrix.calculator.components.Matrix
import io.github.taz03.matrix.calculator.components.MatrixOnValueChange
import io.github.taz03.matrix.calculator.screen.inverse.viewmodel.InverseViewModel

@Composable
fun Inverse(viewModel: InverseViewModel = viewModel { InverseViewModel() }) = Scaffold(
    bottomBar = {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                LabeledIncDecControls(
                    label = "Side",
                    onDecrementClicked = viewModel::decrementSide,
                    onIncrementClicked = viewModel::incrementSide,
                    value = viewModel.side
                )
            }

            Spacer(Modifier.weight(1f))

            Button(onClick = viewModel::calculate) {
                Text("Calculate")
            }
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

        viewModel.inverse?.let {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    buildAnnotatedString {
                        append("A")
                        withStyle(
                            SpanStyle(
                                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                baselineShift = BaselineShift.Superscript
                            )
                        ) {
                            append("-1")
                        }
                        append(" = ")
                    }
                )

                Matrix(matrix = it)
            }
        }
    }
}