package it.willuz.cleandroid.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface IDispatcherManager {
    val background: CoroutineDispatcher
    val ui: CoroutineDispatcher
}

object DispatcherManager: IDispatcherManager {

    override val background: CoroutineDispatcher
        get() = Dispatchers.Default

    override val ui: CoroutineDispatcher
        get() = Dispatchers.Main
}