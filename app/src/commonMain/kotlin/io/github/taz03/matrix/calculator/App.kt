package io.github.taz03.matrix.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    var theme by remember { mutableStateOf(getTheme()) }

    MatrixCalculatorTheme(theme == Theme.DARK) {
        val windowSize = calculateWindowSizeClass()

        when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Expanded -> Row(Modifier.fillMaxSize()) {
                OperationsNavigator(navController)
                Content(
                    theme = theme,
                    onThemeChange = {
                        val newTheme = if (theme == Theme.DARK) Theme.LIGHT else Theme.DARK
                        saveTheme(newTheme)
                        theme = newTheme
                    },
                    navController = navController
                )
            }

            else -> {
                val coroutineScope = rememberCoroutineScope()
                val drawerState = rememberDrawerState(DrawerValue.Closed)

                ModalNavigationDrawer(
                    drawerContent = { OperationsNavigator(navController) },
                    drawerState = drawerState,
                ) {
                    Content(
                        theme = theme,
                        onThemeChange = {
                            val newTheme = if (theme == Theme.DARK) Theme.LIGHT else Theme.DARK
                            saveTheme(newTheme)
                            theme = newTheme
                        },
                        navController = navController
                    ) {
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
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    theme: Theme,
    onThemeChange: () -> Unit,
    navController: NavHostController,
    navigationIcon: @Composable (() -> Unit) = {}
) {
    var title by remember { mutableStateOf("Matrix Calculator") }

    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    navigationIcon = navigationIcon,
                    title = { Text(text = title) },
                    actions = { ThemeToggle(theme, onThemeChange) }
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
