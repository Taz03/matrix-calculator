package io.github.taz03.matrix.calculator

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*

expect fun getTheme(): Theme
expect fun saveTheme(theme: Theme)

enum class Theme {
    LIGHT, DARK
}

@Composable
fun ThemeToggle(
    theme: Theme,
    onThemeToggle: () -> Unit
) = IconButton(onThemeToggle) {
    if (theme == Theme.DARK) Icon(
        imageVector = Icons.Default.LightMode,
        contentDescription = "Switch to light mode"
    )
    else Icon(
        imageVector = Icons.Default.DarkMode,
        contentDescription = "Switch to dark mode"
    )
}
