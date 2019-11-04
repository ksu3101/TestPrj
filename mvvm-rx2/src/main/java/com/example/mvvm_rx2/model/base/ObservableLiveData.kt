package com.example.mvvm_rx2.model.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * ViewModel 에서만 데이터 변경 가능.
 * View(xml) 에서 값 변경 안됨.
 * @author burkd
 * @since 2019-11-04
 */
class CombinedLiveData<T> : LiveData<T>() {
    private val mutableLiveData = MutableLiveData<T>()

    fun set(value: T?) {
        mutableLiveData.value = value
    }

    override fun getValue(): T? {
        return mutableLiveData.value
    }
}

class CombinedNotNullableLiveData<T>(
        initliazedValue: T
) : LiveData<T>() {
    private val mutableLiveData = NotNullableMutableLiveData(initliazedValue)

    fun set(value: T) {
        mutableLiveData.value = value
    }

    override fun getValue(): T {
        return mutableLiveData.value!!
    }
}

private class NotNullableMutableLiveData<T>(
        initliazedValue: T
) : MutableLiveData<T>() {
    init {
        value = initliazedValue
    }
}
