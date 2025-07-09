package io.github.taz03.matrix.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.taz03.matrix.calculator.screen.addition.Addition
import io.github.taz03.matrix.calculator.screen.determinant.Determinant
import io.github.taz03.matrix.calculator.screen.dotproduct.DotProduct
import io.github.taz03.matrix.calculator.screen.inverse.Inverse
import io.github.taz03.matrix.calculator.screen.rank.Rank
import io.github.taz03.matrix.calculator.screen.subtraction.Subtraction
import io.github.taz03.matrix.calculator.screen.transpose.Transpose
import io.github.taz03.matrix.calculator.theme.MatrixCalculatorTheme
import kotlinx.coroutines.launch
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

    @Serializable
    data object Inverse : Screen
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    val coroutineScope = rememberCoroutineScope()

    val navController = rememberNavController()

    var darkMode by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("Matrix Calculator") }
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    MatrixCalculatorTheme(darkMode) {
        ModalNavigationDrawer(
            drawerContent = { OperationsNavigator(navController) },
            drawerState = drawerState,
        ) {
            Scaffold(
                topBar = {
                    Column {
                        CenterAlignedTopAppBar(
                            navigationIcon = {
                                IconButton({
                                    coroutineScope.launch {
                                        drawerState.open()
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Open navigation drawer"
                                    )
                                }
                            },
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
                            }
                        )

                        HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                    }
                }
            ) {
                NavHost(
                    modifier = Modifier.padding(it).fillMaxSize(),
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
                    composable<Screen.Inverse> {
                        Inverse()
                        title = "Inverse"
                    }
                }
            }
        }
    }
}
