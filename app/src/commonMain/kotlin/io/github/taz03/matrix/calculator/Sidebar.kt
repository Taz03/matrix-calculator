package io.github.taz03.matrix.calculator

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.onClick
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun OperationsNavigator(navController: NavController) = Column(
    Modifier.fillMaxWidth(.2f)
        .fillMaxHeight()
        .background(MaterialTheme.colorScheme.surfaceVariant)
        .verticalScroll(rememberScrollState())
) {
    OperationCard(
        name = "Addition",
        description = "Add two matrices, adding corresponding elements",
        onClick = { navController.navigate(Screen.Addition) }
    )

    OperationCard(
        name = "Subtraction",
        description = "Subtract two matrices, subtracting corresponding elements",
        onClick = { navController.navigate(Screen.Subtraction) }
    )

    OperationCard(
        name = "Dot Product",
        description = "Dot Product of two matrices",
        onClick = { navController.navigate(Screen.DotProduct) }
    )

    OperationCard(
        name = "Transpose",
        description = "Transpose a matrix, swapping rows and columns",
        onClick = { navController.navigate(Screen.Transpose) }
    )

    OperationCard(
        name = "Determinant",
        description = "Calculate the determinant of a square matrix",
        onClick = { navController.navigate(Screen.Determinant) }
    )

    OperationCard(
        name = "Rank",
        description = "Calculate the rank of a matrix",
        onClick = { navController.navigate(Screen.Rank) }
    )

    OperationCard(
        name = "Inverse",
        description = "Calculate the inverse of a square matrix",
        onClick = { navController.navigate(Screen.Inverse) }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OperationCard(name: String, description: String, onClick: () -> Unit) = Box(
    modifier = Modifier.onClick { onClick() }
        .padding(4.dp)
        .border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline,
            shape = MaterialTheme.shapes.medium
        )
        .padding(8.dp)
) {
    Column {
        Row {
            Text(
                text = name,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.weight(1f))

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
            )
        }

        Spacer(Modifier.height(5.dp))

        Text(
            text = description,
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = .6f),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}