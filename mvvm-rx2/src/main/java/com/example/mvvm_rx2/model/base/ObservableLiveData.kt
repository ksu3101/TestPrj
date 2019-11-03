package com.example.mvvm_rx2.model.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * @author burkd
 * @since 2019-11-04
 */
class CombinedLiveData<T>: LiveData<T>() {
    private val _liveData = MutableLiveData<T>()
    private val liveData : LiveData<T> = _liveData

    fun get(): T? = liveData.value

    fun set(value: T?) {
        _liveData.value = value
    }
}

class CombinedNotNullableLiveData<T>(
        initliazedValue: T
): LiveData<T>() {
    private val _liveData = NotNullableMutableLiveData(initliazedValue)
    private val liveData : LiveData<T> = _liveData

    fun get(): T = liveData.value!!

    fun set(value: T) {
        _liveData.value = value
    }
}

private class NotNullableMutableLiveData<T>(
        initliazedValue: T
) : MutableLiveData<T>() {
    init {
        value = initliazedValue
    }
}