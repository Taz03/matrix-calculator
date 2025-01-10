package io.github.taz03.matrix.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import io.github.taz03.matrix.calculator.screen.determinant.Determinant
import io.github.taz03.matrix.calculator.screen.dotproduct.DotProduct
import io.github.taz03.matrix.calculator.screen.rank.Rank
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
    data object DotProduct : Screen

    @Serializable
    data object Transpose : Screen

    @Serializable
    data object Determinant : Screen

    @Serializable
    data object Rank : Screen
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    var darkMode by remember { mutableStateOf(false) }

    val navController = rememberNavController()

    var title by remember { mutableStateOf("Matrix Calculator") }

    MatrixCalculatorTheme(darkMode) {
        Surface {
            Row {
                OperationsNavigator(navController)

                Scaffold(
                    topBar = {
                        Column {
                            TopAppBar(
                                title = { Text(text = title) },
                                actions = {
                                    IconButton({ darkMode = !darkMode }) {
                                        if (darkMode) Icon(
                                            imageVector = Icons.Default.LightMode,
                                            contentDescription = "Switch to light mode"
                                        )
                                        else Icon(
                                            imageVector = Icons.Default.DarkMode,
                                            contentDescription = "Switch to dark mode"
                                        )
                                    }
                                },
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
                        composable<Screen.DotProduct> {
                            DotProduct()
                            title = "Dot Product"
                        }
                        composable<Screen.Transpose> {
                            Transpose()
                            title = "Transpose"
                        }
                        composable<Screen.Determinant> {
                            Determinant()
                            title = "Determinant"
                        }
                        composable<Screen.Rank> {
                            Rank()
                            title = "Rank"
                        }
                    }
                }
            }
        }
    }
}