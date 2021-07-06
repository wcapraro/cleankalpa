package it.willuz.cleandroid.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

interface IDispatcherManager {
    val background: CoroutineDispatcher
    val ui: CoroutineDispatcher
    fun presenterScope(): CoroutineScope
}

object DispatcherManager: IDispatcherManager {

    override val background: CoroutineDispatcher
        get() = Dispatchers.Default

    override val ui: CoroutineDispatcher
        get() = Dispatchers.Main

    override fun presenterScope(): CoroutineScope =
        CoroutineScope(Executors.newSingleThreadExecutor().asCoroutineDispatcher())
}