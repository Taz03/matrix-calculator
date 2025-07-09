package io.github.taz03.matrix.calculator

import kotlinx.browser.localStorage

private const val THEME_KEY = "theme"

actual fun getTheme(): Theme = when (localStorage.getItem(THEME_KEY)) {
    Theme.DARK.toString() -> Theme.DARK
    else -> Theme.LIGHT
}

actual fun saveTheme(theme: Theme) = localStorage.setItem(THEME_KEY, theme.toString())
