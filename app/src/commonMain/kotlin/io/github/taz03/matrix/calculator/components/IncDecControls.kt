package io.github.taz03.matrix.calculator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IncDecControls(
    onDecrementClicked: () -> Unit,
    onIncrementClicked: () -> Unit,
    value: Int
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
    onDecrementClicked: () -> Unit,
    onIncrementClicked: () -> Unit,
    value: Int
) = Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    IncDecControls(onDecrementClicked, onIncrementClicked, value)
    Text(label)
}
