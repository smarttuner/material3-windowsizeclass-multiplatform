package androidx.compose.foundation.utils

import androidx.compose.material3.windowsizeclass.objc.KeyValueObserverProtocol
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExportObjCClass
import platform.darwin.NSObject

@ExportObjCClass
internal class ObserverObject(
    private val onChange: () -> Unit,
) : NSObject(), KeyValueObserverProtocol {
    override fun observeValueForKeyPath(
        keyPath: String?,
        ofObject: Any?,
        change: Map<Any?, *>?,
        context: COpaquePointer?,
    ) {
        onChange()
    }
}
