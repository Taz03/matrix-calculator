package io.github.taz03.matrix.calculator

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun OperationsNavigator(navController: NavController) = Column(
    modifier = Modifier.fillMaxHeight()
        .width(250.dp)
        .background(MaterialTheme.colorScheme.surfaceVariant)
        .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.spacedBy(8.dp)
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
fun OperationCard(name: String, description: String, onClick: () -> Unit) = Column(
    modifier = Modifier.fillMaxWidth()
        .padding(4.dp)
        .border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline,
            shape = MaterialTheme.shapes.medium
        )
        .clip(MaterialTheme.shapes.medium)
        .clickable(onClick = onClick)
        .padding(8.dp)
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = name,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.titleLarge
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null
        )
    }

    Spacer(Modifier.height(5.dp))

    Text(
        text = description,
        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = .6f),
        style = MaterialTheme.typography.bodyMedium
    )
}
