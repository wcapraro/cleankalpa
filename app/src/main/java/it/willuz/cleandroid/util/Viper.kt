package it.willuz.cleandroid.util

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import java.lang.ref.WeakReference

/**
 * These interfaces provide a common supertype for all major
 * VIPER components.
 */
class Viper {

    // Router
    interface ViperRouter

    // Presenter
    interface ViperPresenter

    // Interactor
    interface ViperInteractor

    // View
    interface ViperView

    // Interactor Output
    interface ViperInteractorOutput

}

/**
 * Defines common behavior shared across all VIPER activities. Note that every
 * such activity must keep a reference to a presenter which complies to the
 * [Viper.ViperPresenter] interface.
 */
abstract class BaseViperActivity<T> : AppCompatActivity(),
    Viper.ViperView where T: Viper.ViperPresenter {

    // Reference to presenter
    var presenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
    }


    abstract fun instantiatePresenter(): T?
}

/**
 * Base class for all VIPER interactors. All interactors must keep a reference to an
 * object of type [Viper.ViperInteractorOutput], which allows the interactor to communicate with
 * other VIPER actors that implement that interface (typically presenters).
 */
abstract class BaseViperInteractor<out T>(val output: T): Viper.ViperInteractor where T: Viper.ViperInteractorOutput

/**
 * A VIPER Presenter is a component that interacts with the View by handing
 * user input and driving the rendering of UI-related data. Every Presenter must
 * therefore have a reference to a [Viper.ViperView] object. Moreover, the Presenter
 * coordinates both the [Viper.ViperInteractor] and the [Viper.ViperRouter]. Note that the
 * Presenter should be aware of the View's lifecycle as dictated by the Android framework.
 * See [LifecycleObserver] for more details.
 */
abstract class BaseViperPresenter<V, I, R>(var view: V?): Viper.ViperPresenter,
    LifecycleObserver
        where V: Viper.ViperView,
              I: Viper.ViperInteractor,
              R: Viper.ViperRouter
{

    // Reference to interactor
    var interactor: I? = null

    // Reference to router
    var router: R? = null

    // A coroutine scope for this presenter!
    var presenterScope: CoroutineScope? = null

    fun initLifecycle() {
        (view as LifecycleOwner).lifecycle.addObserver(this)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun lifecycleOnCreate() {
        presenterScope = DispatcherManager.presenterScope()
        interactor = createInteractor()
        router = createRouter()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun lifecycleOnDestroy() {
        presenterScope?.cancel("onDestroy() called")
        presenterScope = null
        interactor = null
        router = null
    }

    abstract fun createInteractor(): I?
    abstract fun createRouter(): R?
}

/**
 * Base class for all the VIPER Router modules.
 */
abstract class BaseViperRouter: Viper.ViperRouter



