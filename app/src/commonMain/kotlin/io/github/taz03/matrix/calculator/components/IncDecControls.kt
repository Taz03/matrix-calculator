package io.github.taz03.matrix.calculator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun IncDecControls(
    value: Int,
    onDecrementClicked: () -> Unit,
    onIncrementClicked: () -> Unit,
) = SingleChoiceSegmentedButtonRow {
    SegmentedButton(
        selected = false,
        onClick = onDecrementClicked,
        shape = SegmentedButtonDefaults.itemShape(0, 3),
        icon = {},
        label = { Text("-") }
    )

    SegmentedButton(
        selected = false,
        onClick = {},
        shape = SegmentedButtonDefaults.itemShape(1, 3),
        enabled = false,
        icon = {},
        label = { Text(value.toString()) }
    )

    SegmentedButton(
        selected = false,
        onClick = onIncrementClicked,
        shape = SegmentedButtonDefaults.itemShape(2, 3),
        icon = {},
        label = { Text("+") }
    )
}

@Composable
fun LabeledIncDecControls(
    label: String,
    value: Int,
    onDecrementClicked: () -> Unit,
    onIncrementClicked: () -> Unit,
) = Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    IncDecControls(value, onDecrementClicked, onIncrementClicked)
    Text(label)
}
