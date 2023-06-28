package androidx.compose.foundation

import androidx.compose.runtime.Composable

/**
 * Gets the current system theme style
 */
@Composable
actual fun isPlatformInDarkTheme(): Boolean {
    return isSystemInDarkTheme()
}