package io.github.taz03.matrix.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.taz03.matrix.calculator.screen.addition.Addition
import io.github.taz03.matrix.calculator.screen.multiplication.Multiplication
import io.github.taz03.matrix.calculator.screen.subtraction.Subtraction
import io.github.taz03.matrix.calculator.screen.transpose.Transpose
import io.github.taz03.matrix.calculator.theme.MatrixCalculatorTheme
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Serializable
sealed interface Screen {
    @Serializable
    data object Addition : Screen

    @Serializable
    data object Subtraction : Screen

    @Serializable
    data object Multiplication : Screen

    @Serializable
    data object Transpose : Screen
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    var title by remember { mutableStateOf("Matrix Calculator") }

    MatrixCalculatorTheme(false) {
        Surface {
            Row {
                OperationsNavigator(navController)

                Scaffold(
                    topBar = {
                        Column {
                            TopAppBar(
                                title = { Text(text = title) },
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.surface,
                                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                )
                            )

                            HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                        }
                    }
                ) { padding ->
                    NavHost(
                        modifier = Modifier.padding(padding).fillMaxSize(),
                        navController = navController,
                        startDestination = Screen.Addition
                    ) {
                        composable<Screen.Addition> {
                            Addition()
                            title = "Addition"
                        }
                        composable<Screen.Subtraction> {
                            Subtraction()
                            title = "Subtraction"
                        }
                        composable<Screen.Multiplication> {
                            Multiplication()
                            title = "Multiplication"
                        }
                        composable<Screen.Transpose> {
                            Transpose()
                            title = "Transpose"
                        }
                    }
                }
            }
        }
    }
}