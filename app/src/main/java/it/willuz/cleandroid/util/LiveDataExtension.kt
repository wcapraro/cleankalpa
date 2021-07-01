package it.willuz.cleandroid.util

import androidx.lifecycle.MutableLiveData

inline fun <T> MutableLiveData<T>?.reassign(mapper: (T) -> (T)) {
    this?.value?.let { v ->
        this.postValue(mapper(v))
    }
}