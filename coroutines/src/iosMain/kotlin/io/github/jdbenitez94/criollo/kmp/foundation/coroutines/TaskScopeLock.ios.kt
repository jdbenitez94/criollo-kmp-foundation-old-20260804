package io.github.jdbenitez94.criollo.kmp.foundation.coroutines

import platform.Foundation.NSLock

internal actual class TaskScopeLock {
    private val lock = NSLock()

    actual fun <T> withLock(block: () -> T): T {
        lock.lock()
        try {
            return block()
        } finally {
            lock.unlock()
        }
    }
}
