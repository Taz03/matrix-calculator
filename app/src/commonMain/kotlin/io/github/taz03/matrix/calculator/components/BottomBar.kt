package io.github.taz03.matrix.calculator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun BottomBar(
    onCalculate: () -> Unit,
    content: @Composable () -> Unit,
) = Row(
    modifier = Modifier.fillMaxWidth()
        .background(MaterialTheme.colorScheme.secondaryContainer)
        .padding(15.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    val windowsSize = calculateWindowSizeClass()

    when (windowsSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> Column(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }

        else -> Row(
            horizontalArrangement = Arrangement.spacedBy(30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }

    Button(onCalculate) {
        Text("Calculate")
    }
}
