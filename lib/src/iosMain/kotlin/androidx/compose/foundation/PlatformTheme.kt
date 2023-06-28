package androidx.compose.foundation

import androidx.compose.foundation.utils.ObserverObject
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.interop.LocalUIViewController
import platform.Foundation.NSKeyValueObservingOptionNew
import platform.Foundation.addObserver
import platform.Foundation.removeObserver
import platform.UIKit.UIUserInterfaceStyle

/**
 * Gets the current system theme style
 */
@Composable
actual fun isPlatformInDarkTheme(): Boolean {
    val uiViewController = LocalUIViewController.current

    var isPlatformInDarkTheme by remember(uiViewController) {
        mutableStateOf(false)
    }

    DisposableEffect(uiViewController) {
        val observer = ObserverObject {
            isPlatformInDarkTheme = uiViewController.traitCollection.userInterfaceStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark
        }

        uiViewController.view.layer.addObserver(
            observer = observer,
            forKeyPath = "effectiveUserInterfaceStyle",
            options = NSKeyValueObservingOptionNew,
            context = null,
        )

        onDispose {
            uiViewController.view.layer.removeObserver(
                observer = observer,
                forKeyPath = "effectiveUserInterfaceStyle",
            )
        }
    }

    return isPlatformInDarkTheme
}

